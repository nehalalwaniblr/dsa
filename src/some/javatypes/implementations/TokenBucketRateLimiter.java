package some.javatypes.implementations;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class TokenBucketRateLimiter {

    // Maximum tokens a bucket can hold
    private final long capacity;

    // Tokens generated per second
    private final long refillRatePerSecond;

    // One bucket per key/user/client
    private final ConcurrentHashMap<String, Bucket> buckets =
            new ConcurrentHashMap<>();

    public TokenBucketRateLimiter(
            long capacity,
            long refillRatePerSecond) {

        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
    }

    public boolean tryAcquire(String key) {

        // Create bucket lazily if absent
        Bucket bucket = buckets.computeIfAbsent(
                key,
                k -> new Bucket(capacity)
        );

        // Lock only THIS bucket
        bucket.lock.lock();

        try {

            refill(bucket);

            // No token available
            if (bucket.tokens == 0) {
                return false;
            }

            // Consume one token
            bucket.tokens--;

            return true;

        } finally {
            bucket.lock.unlock();
        }
    }

    private void refill(Bucket bucket) {

        long now = System.nanoTime();

        // Time elapsed since last refill
        long elapsedNanos =
                now - bucket.lastRefillTimestamp;

        // Convert elapsed time -> generated tokens
        long tokensToAdd =
                (elapsedNanos * refillRatePerSecond)
                        / 1_000_000_000L;

        // Not enough time passed to generate even 1 token
        if (tokensToAdd <= 0) {
            return;
        }

        // Add tokens but never exceed capacity
        bucket.tokens = Math.min(
                capacity,
                bucket.tokens + tokensToAdd
        );

        /*
         Advance timestamp ONLY by consumed time.

         This preserves leftover fractional nanoseconds.

         Example:
         Rate = 5/sec
         1 token every 200ms

         If 450ms passed:
         generate 2 tokens (400ms consumed)
         preserve remaining 50ms
        */
        long nanosConsumed =
                (tokensToAdd * 1_000_000_000L)
                        / refillRatePerSecond;

        bucket.lastRefillTimestamp += nanosConsumed;
    }

    static class Bucket {

        long tokens;

        long lastRefillTimestamp;

        ReentrantLock lock = new ReentrantLock();

        Bucket(long capacity) {

            // Start full
            this.tokens = capacity;

            this.lastRefillTimestamp =
                    System.nanoTime();
        }
    }
}