package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
*
* Given an integer array nums, return the length of the longest strictly increasing subsequence.



Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4
Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1


Constraints:

1 <= nums.length <= 2500
-104 <= nums[i] <= 104


Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
* */
public class LongestIncreasingSubsequence {
    /*
     * Multiple approaches:
     * 1. Recursion+memoization- O(n2);space complexity O(2501*2501): use take and skip method ;for each index either take or skip; if take then ensure the previous index value < current index; take max of skip and take
     * 2. Bottom up approach - O(n2);space complexity O(n): maintain a state array initialized with 1 as each element is a sub seq; maintain 2 loops i=0 to n; j=i+1 to j<i;if(a[j]<a[i]) then check dp[] and get max of dp[i]+1 or dp[i]
     * 3. Lazy/patience sort - O(n log(n)): here iterate through the array and create another temp array where each element in input list is compared to fnd an element just greater than current element; if so replace that with the current element; if not add it to the array; This form a kind of bucket; length of array would be the answer
     * */
    int dp[][];

    /*1. Recursion+memoization -little difficult to code compared to 2nd*/
    public int lengthOfLIS(int[] nums) {
        dp = new int[2501][2501];
        for (int[] i : dp
        ) {
            Arrays.fill(i, -1);
        }
        return solve(nums, 0, -1);
    }

    int solve(int[] nums, int i, int previousIndex) {
        if (i >= nums.length)
            return 0;
        if (previousIndex != -1 && dp[i][previousIndex] != -1)
            return dp[i][previousIndex];
        int take = 0;
        if (previousIndex == -1 || nums[i] > nums[previousIndex]) {
            take = 1 + solve(nums, i + 1, i);
        }
        int skip = solve(nums, i + 1, previousIndex);
        if (previousIndex != -1)
            dp[i][previousIndex] = Integer.max(skip, take);
        return Integer.max(skip, take);
    }

    /*2. Bottom up approach*/
    public int lengthOfLIS2(int[] nums) {
        int maxLIS = 1;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    maxLIS = Math.max(maxLIS, dp[i]);
                }
            }
        }
        return maxLIS;
    }

    /*3. Patience sort*/
    public int lengthOfLIS3(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = binarySearch(result, nums[i]);
            if (index == result.size()) {
                result.add(nums[i]);
            } else {
                result.set(index, nums[i]);
            }
        }
        return result.size();
    }

    int binarySearch(List<Integer> array, int num) {
        int start = 0;
        int end = array.size();
        int result = array.size();
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (num > array.get(mid)) {
                start = mid + 1;
            } else  {
                result = mid;
                end = mid;
            }
        }
        return result;
    }

    public static void main(String[] args) {

//        System.out.println(new LongestIncreasingSubsequence().lengthOfLIS3(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
//        System.out.println(new LongestIncreasingSubsequence().lengthOfLIS3(new int[]{0, 1, 0, 3, 2, 3}));
        System.out.println(new LongestIncreasingSubsequence().lengthOfLIS3(new int[]{7, 7, 7, 7, 7, 7, 7}));
//        System.out.println(new LongestIncreasingSubsequence().lengthOfLIS3(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6}));
    }
}
