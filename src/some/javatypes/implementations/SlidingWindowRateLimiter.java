package some.javatypes.implementations;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowRateLimiter {

    private final int maxRequests;
    private final long windowMillis;

    // Key → deque of request timestamps (milliseconds)
    // ConcurrentHashMap: safe map-level ops, no corrupt structure
    // Per-key lock on the Deque: compound ops on one key are atomic
    private final ConcurrentHashMap<String, Deque<Long>> requestLog
        = new ConcurrentHashMap<>();

    public SlidingWindowRateLimiter(int maxRequests, long windowMillis) {
        this.maxRequests  = maxRequests;
        this.windowMillis = windowMillis;
    }

    public boolean allow(String key) {
        long now = System.currentTimeMillis();

        // computeIfAbsent is atomic — safe for concurrent new keys
        Deque<Long> timestamps = requestLog
            .computeIfAbsent(key, k -> new ArrayDeque<>());

        // Lock on the Deque: evict + count + add must be atomic per key
        // Two different keys lock on different objects — no cross-key contention
        synchronized (timestamps) {

            // 1. Evict timestamps outside the current window
            long windowStart = now - windowMillis;
            while (!timestamps.isEmpty()
                    && timestamps.peekFirst() <= windowStart) {
                timestamps.pollFirst();
            }

            // 2. Check limit
            if (timestamps.size() >= maxRequests) {
                return false;  // deny
            }

            // 3. Record this request
            timestamps.addLast(now);
            return true;  // allow
        }
    }
}

// Usage: 10 requests per second per API key
// SlidingWindowRateLimiter limiter =
//     new SlidingWindowRateLimiter(10, 1000);