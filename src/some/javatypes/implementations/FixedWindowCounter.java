package some.javatypes.implementations;

import java.util.concurrent.ConcurrentHashMap;
/*What to say before writing code (this is the Staff-level signal)
Tell the interviewer:

"Is this per-user or global?" → per userId
"Fixed window or sliding window?" → start with fixed, offer sliding
"Single JVM or distributed?" → single JVM first, mention Redis for distributed
"Thread-safe?" → always assume yes

Then say: "I'll implement a fixed window counter first, then discuss the sliding window improvement."

Why per-key locking and not a global lock? A global synchronized(this) means all users block each other. Locking only the per-user long[] means threads for user A and user B run completely in parallel. This is exactly the principle behind ConcurrentHashMap — narrow the lock scope.
The weakness to volunteer proactively: "A user can send 100 requests at 11:59:59 and 100 more at 12:00:01 — effectively 200 in 2 seconds. That's the boundary burst problem. A sliding window log solves this."
*/
public class FixedWindowCounter {
    private final long windowSize;
    private final int maxRequests;
    // // per-user: [requestCount, windowStartTime]
    private final ConcurrentHashMap<String, long[]> userLimit = new ConcurrentHashMap<>();

    FixedWindowCounter(long windowSize, int maxRequests) {
        this.windowSize = windowSize;
        this.maxRequests = maxRequests;
    }

    public boolean isAllowed(String userName){
        long now = System.currentTimeMillis();
        userLimit.computeIfAbsent(userName, k-> new long[]{0, now});
        long[] window = userLimit.get(userName);
        synchronized (window){
            if(now - window[1]>=windowSize){
                window[0] = 0;
                window[1] = now;
            }
            if(window[0]<maxRequests) {
                window[0]++;
                return true;
            }
            return false;
        }
    }
}
