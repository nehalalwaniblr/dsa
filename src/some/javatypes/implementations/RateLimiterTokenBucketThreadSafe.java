package some.javatypes.implementations;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class RateLimiterTokenBucketThreadSafe {
    private final long capacity;
    private final int refillRate;
    private ConcurrentHashMap<String, Bucket> map = new ConcurrentHashMap<>();

    RateLimiterTokenBucketThreadSafe(long capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
    }

    public boolean tryAcquire(String key) {
        long now = System.currentTimeMillis();
        Bucket bucket = map.get(key);
        bucket.lock.lock();
        try {
            refillBucket(key, bucket);
            if (bucket.tokens <= 0)
                return false;
            bucket.tokens--;
            return true;
        } finally {
            bucket.lock.unlock();
        }
    }

    private void refillBucket(String key, Bucket bucket) {
        long now = System.nanoTime();
        // Time elapsed since last refill
        long elapsedNanos =
                now - bucket.lastRefillTimeStamp;

        // Convert elapsed time -> generated tokens
        long tokensToAdd =
                (elapsedNanos * refillRate)
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
                        / refillRate;

        bucket.lastRefillTimeStamp += nanosConsumed;
    }

}


class Bucket {
    long tokens;
    long lastRefillTimeStamp;
    ReentrantLock lock = new ReentrantLock();

}