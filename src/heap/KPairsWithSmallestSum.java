package heap;

import java.util.*;

/*You are given two integer arrays nums1 and nums2 sorted in non-decreasing order and an integer k.

Define a pair (u, v) which consists of one element from the first array and one element from the second array.

Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.



Example 1:

Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]]
Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Example 2:

Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
Output: [[1,1],[1,1]]
Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]*/
public class KPairsWithSmallestSum {
    /*
    * Try with brut force
    * Start with 0th element of second array and take all elements from 1st or till we dont reach k; keep adding in min heap
    * Now iterate over till k is not 0 and min heap isnt empty
    * keep adding result from min heap
    * keep adding second array's element + first arrays element to heap
    * https://www.youtube.com/watch?v=PiGYS7BbV_Q&ab_channel=codestorywithMIK
    * */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        List<List<Integer>> res = new ArrayList<>();

        if (nums1.length == 0 || nums2.length == 0 || k == 0) return res;
        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            minHeap.offer(new int[]{nums1[i] + nums2[0], i, 0});
        }

        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] cur = minHeap.poll();
            int i = cur[1];
            int j = cur[2];
            res.add(Arrays.asList(nums1[i], nums2[j]));  // Convert int[] to List<Integer>

            if (j + 1 < nums2.length) {
                minHeap.offer(new int[]{nums1[i] + nums2[j + 1], i, j + 1});
            }
        }
        return res;
    }

    public static void main(String[] args) {
        new KPairsWithSmallestSum().kSmallestPairs(new int[]{1,7,11}, new int[]{2,4,6},3);
        new KPairsWithSmallestSum().kSmallestPairs(new int[]{1,1,2}, new int[]{1,2,3},2);

    }
}

