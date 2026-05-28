package some.javatypes.implementations;

/*
Primitives vs Wrapper Classes — why int not Integer
Short answer: performance, null-safety, and intent.
1. No boxing/unboxing overhead. Integer is an object on the heap. Every time you do arithmetic or comparison, Java has to unbox it to a primitive anyway. For a rate limiter called thousands of times per second, that's unnecessary allocation pressure on the GC. int lives on the stack — zero heap cost.
2. Null-safety. Integer maxRequests can be null, which means maxRequests - 1 would throw a NullPointerException at runtime. int cannot be null — the compiler enforces it. For a config field that must always have a value, int expresses that contract clearly.
3. Memory. int = 4 bytes. Integer = 16 bytes (object header + 4 bytes int + padding). For fields held in millions of rate limiter instances, this adds up.
4. == semantics. Integer a = 1000; Integer b = 1000; a == b is false (different objects). int a = 1000; int b = 1000; a == b is true. Bugs from accidental reference comparison on wrappers are a real footgun.
Rule of thumb: use primitives for fields/local variables where you control the lifecycle. Use wrappers (Integer, Long) only when you need nullability (e.g. Optional values), generics (e.g. List<Integer> — generics can't hold primitives), or when an API forces it (e.g. Map<String, Integer>).
*/
public class RateLimiterTokenBucket {
    private final int bucketCapacity;
    private final int refillRate;
    private long tokens;
    private long lastRefillTimeStamp;

    public RateLimiterTokenBucket(int bucketCapacity, int refillRate) {
        this.bucketCapacity = bucketCapacity;
        this.refillRate = refillRate;
        this.tokens = bucketCapacity;
        this.lastRefillTimeStamp = System.nanoTime(); // ← fixed
    }

    public synchronized boolean allowRequest() {
        refill();
        if (tokens >= 1) {
            tokens--;
            return true;
        }
        return false;
    }

    private void refill() { // ← private
        long now = System.nanoTime();
        long newTokens = (now - lastRefillTimeStamp) * refillRate / 1000000000L; // ← fixed order + L suffix
        tokens = Math.min(bucketCapacity, newTokens + tokens);
        lastRefillTimeStamp = now;
    }
}
