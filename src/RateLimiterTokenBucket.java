import java.util.concurrent.atomic.AtomicLong;
/*🚀 Optimization 1 — Reduce Time Calculations

Instead of calculating refill every request:

do refill only when needed
🚀 Optimization 2 — Use LongAdder (Better than AtomicLong)

For heavy contention:

LongAdder counter = new LongAdder();

Better than AtomicLong under high concurrency.

🚀 Optimization 3 — Per-Key Rate Limiter (Multi-user)
ConcurrentHashMap<String, TokenBucketRateLimiter> limiterMap = new ConcurrentHashMap<>();

public boolean allowRequest(String userId) {
    limiterMap.putIfAbsent(userId, new TokenBucketRateLimiter(100, 10));
    return limiterMap.get(userId).tryAcquire();
}
4️⃣ Handling 100k QPS in Real Systems

I’d implement a token bucket using AtomicLong with CAS to ensure thread safety without locks. This allows high throughput under concurrency, and I’d combine it with local caching and distributed rate limiting for scalability.
🔴 Problem with AtomicLong

AtomicLong uses a single memory location.

At high QPS:

100 threads → same variable → contention

Internally:

CAS retry loops → threads keep failing → CPU waste

This is called:

contention hotspot
*/
public class RateLimiterTokenBucket {

    //Max capacity of the bucket
    private final long capacity;
    //Bucket refill at
    private final long refillRatePerSecond;

    //Number of tokens one has
    private final AtomicLong tokens;
    //Last refilled at
    private final AtomicLong lastRefillTimestamp;
    public RateLimiterTokenBucket(long capacity, long refillRatePerSecond){
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;

        this.tokens = new AtomicLong(capacity);
        this.lastRefillTimestamp = new AtomicLong(System.nanoTime());
    }

    public boolean isAllowed(){
        while (true){
            Long currentTime = System.nanoTime();
            long currentTokens = this.tokens.get();

            long lastRefilledAt = this.lastRefillTimestamp.get();

            //new tokens to add
            long timeElapsed = lastRefilledAt - currentTime;
            long tokensToAdd = (timeElapsed*refillRatePerSecond)/100000000;
            long newTokens = currentTokens;
            if(tokensToAdd>0){
                newTokens = Math.min(capacity, currentTokens+tokensToAdd);
                if(!lastRefillTimestamp.compareAndSet(lastRefilledAt, currentTime))
                    continue;
            }
            if(newTokens==0)
                return false;
            //consume one token and reduce token count by 1
            if(tokens.compareAndSet(currentTokens,newTokens-1))
                return true;

        }
    }


}
