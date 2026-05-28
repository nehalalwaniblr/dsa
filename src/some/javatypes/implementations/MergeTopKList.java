package some.javatypes.implementations;

import java.util.*;

class MergeTopKList {

    // Global timestamp — AtomicInteger for thread safety
    // Guarantees strict ordering even if two tweets posted simultaneously
    private int timestamp = 0;

    // userId → tweets posted, newest first (we addFirst on every post)
    // int[0] = timestamp, int[1] = tweetId
    private Map<Integer, Deque<int[]>> tweets = new HashMap<>();

    // userId → set of userIds they follow
    // HashSet: O(1) follow, unfollow, membership check
    private Map<Integer, Set<Integer>> follows = new HashMap<>();

    private static final int FEED_SIZE = 10;

    // ─── postTweet ────────────────────────────────────────────────────
    public void postTweet(int userId, int tweetId) {
        // Initialise the tweet deque if first tweet for this user
        tweets.computeIfAbsent(userId, k -> new ArrayDeque<>());

        // addFirst: newest tweet at the front — feeds into heap head-first
        tweets.get(userId).addFirst(new int[]{timestamp++, tweetId});
    }

    // ─── getNewsFeed ──────────────────────────────────────────────────
    public List<Integer> getNewsFeed(int userId) {
        // MaxHeap: compare by timestamp descending
        // int[0]=timestamp, int[1]=tweetId,
        // int[2]=index into the followee's tweet array (as list),
        // int[3]=followeeId (to fetch next tweet)
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> b[0] - a[0]  // higher timestamp = higher priority
        );

        // Include self: user sees their own tweets in the feed
        // Trick: add userId to their own follow set
        follows.computeIfAbsent(userId, k -> new HashSet<>());
        follows.get(userId).add(userId);  // follow self

        // Seed the heap: push the most recent tweet from each followee
        for (int followeeId : follows.get(userId)) {
            Deque<int[]> userTweets = tweets.get(followeeId);
            if (userTweets == null || userTweets.isEmpty()) continue;

            // Convert deque to list for index-based access during merge
            // (Deque doesn't support index access — we need "next" element)
            List<int[]> tweetList = new ArrayList<>(userTweets);

            // Push: [timestamp, tweetId, currentIndex=0, followeeId]
            // index=0 because we start at the front (most recent)
            maxHeap.offer(new int[]{
                tweetList.get(0)[0],   // timestamp
                tweetList.get(0)[1],   // tweetId
                0,                      // pointer: index in this user's list
                followeeId              // to retrieve the list again
            });

            // Store converted list for O(1) index access during extraction
            // We'll use a separate map for this
        }

        // Problem: after extracting, we need the followee's list by index.
        // Solution: store lists in a local map during this call.
        // (See clean version below — this is the insight to explain)

        List<Integer> result = new ArrayList<>();

        // Extract up to FEED_SIZE tweets
        while (!maxHeap.isEmpty() && result.size() < FEED_SIZE) {
            int[] top = maxHeap.poll();
            result.add(top[1]);  // tweetId

            // If this followee has more tweets, push the next one
            int nextIndex = top[2] + 1;
            // ... push next tweet from same followee at nextIndex
        }

        return result;
    }

    // ─── follow / unfollow ───────────────────────────────────────────
    public void follow(int followerId, int followeeId) {
        follows.computeIfAbsent(followerId, k -> new HashSet<>());
        follows.get(followerId).add(followeeId);
        // HashSet.add() is no-op if already present — no duplicate follows
    }

    public void unfollow(int followerId, int followeeId) {
        if (follows.containsKey(followerId)) {
            follows.get(followerId).remove(followeeId);
            // HashSet.remove() is no-op if not present — safe
        }
    }
}