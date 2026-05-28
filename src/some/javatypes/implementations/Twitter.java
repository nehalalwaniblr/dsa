package some.javatypes.implementations;

import java.util.*;

class Twitter {

    private int timestamp = 0;
    private final Map<Integer, List<int[]>> tweets  = new HashMap<>();
    private final Map<Integer, Set<Integer>> follows = new HashMap<>();
    private static final int FEED_SIZE = 10;

    public void postTweet(int userId, int tweetId) {
        tweets.computeIfAbsent(userId, k -> new ArrayList<>());
        // addFirst equivalent: add to front using index 0
        // Or: add to back, iterate in reverse during feed — simpler
        tweets.get(userId).add(0, new int[]{timestamp++, tweetId});
        // Note: add(0, x) on ArrayList is O(n) — shifts elements.
        // For interview: acceptable. Production: use ArrayDeque or LinkedList.
    }

    public List<Integer> getNewsFeed(int userId) {
        // MaxHeap: [timestamp, tweetId, listIndex, followeeId]
        PriorityQueue<int[]> heap = new PriorityQueue<>(
            (a, b) -> b[0] - a[0]
        );

        // Always include self
        follows.computeIfAbsent(userId, k -> new HashSet<>());
        follows.get(userId).add(userId);

        // Seed heap with most recent tweet from each followee
        for (int fid : follows.get(userId)) {
            List<int[]> list = tweets.get(fid);
            if (list == null || list.isEmpty()) continue;
            // index 0 = most recent (we insert at front in postTweet)
            heap.offer(new int[]{list.get(0)[0], list.get(0)[1], 0, fid});
        }

        List<Integer> result = new ArrayList<>();

        while (!heap.isEmpty() && result.size() < FEED_SIZE) {
            int[] top = heap.poll();
            result.add(top[1]);  // tweetId

            int nextIdx = top[2] + 1;
            int followeeId = top[3];
            List<int[]> list = tweets.get(followeeId);

            // If followee has more tweets, push next candidate
            if (nextIdx < list.size()) {
                heap.offer(new int[]{
                    list.get(nextIdx)[0],  // timestamp
                    list.get(nextIdx)[1],  // tweetId
                    nextIdx,               // pointer advances
                    followeeId
                });
            }
        }

        return result;
    }

    public void follow(int followerId, int followeeId) {
        follows.computeIfAbsent(followerId, k -> new HashSet<>());
        follows.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (follows.containsKey(followerId)) {
            follows.get(followerId).remove(followeeId);
        }
    }
}