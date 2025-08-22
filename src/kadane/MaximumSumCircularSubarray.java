package kadane;
/*
* Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.

A circular array means the end of the array connects to the beginning of the array. Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].

A subarray may only include each element of the fixed buffer nums at most once. Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.
*
* Example 1:

Input: nums = [1,-2,3,-2]
Output: 3
Explanation: Subarray [3] has maximum sum 3.
Example 2:

Input: nums = [5,-3,5]
Output: 10
Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.
Example 3:

Input: nums = [-3,-2,-3]
Output: -2
Explanation: Subarray [-2] has maximum sum -2.


Constraints:

n == nums.length
1 <= n <= 3 * 104
-3 * 104 <= nums[i] <= 3 * 104
*
*
*
* SOLUTION:
* In a circular array, the maximum sum can happen in two ways:

Case 1 — Normal Subarray (no wrap-around)
This is just the classic Kadane’s algorithm.

Example: [5, -2, 3, 4] → Kadane gives 10.

Case 2 — Circular Subarray (wrap-around)
The maximum subarray might include elements from both ends.

Think of it as:

bash
Copy
Edit
Max circular sum = Total sum of array - Minimum subarray sum
Why?
If you remove the minimum subarray, the remaining elements (on both ends) form the maximum circular sum.


* */
public class MaximumSumCircularSubarray {
    public int maxSubarraySumCircular(int[] nums) {
        int maxSum = kadanesMaxSum(nums);
        int minSum = kadanesMinSum(nums);
        int totalSum = 0;
        for(int i=0;i<nums.length;i++){
            totalSum+=nums[i];
        }
        if(maxSum>0)
            return Math.max(maxSum,totalSum-minSum);
        return maxSum;
    }

    int kadanesMaxSum(int[] nums){
        int currentSubArraySum = nums[0];
        int maxSubArraySum = nums[0];
        for(int i=1;i<nums.length;i++){
            currentSubArraySum = Math.max(currentSubArraySum+nums[i],nums[i]);
            maxSubArraySum = Math.max(currentSubArraySum,maxSubArraySum);
        }
        return maxSubArraySum;
    }

    int kadanesMinSum(int[] nums){
        int currentSubArraySum = nums[0];
        int minSubArraySum = nums[0];
        for(int i=1;i<nums.length;i++){
            currentSubArraySum = Math.min(currentSubArraySum+nums[i],nums[i]);
            minSubArraySum = Math.min(currentSubArraySum,minSubArraySum);
        }
        return minSubArraySum;
    }

    public static void main(String[] args) {
        System.out.println(new MaximumSumCircularSubarray().maxSubarraySumCircular(new int[]{1,-2,3,-2}));
        System.out.println(new MaximumSumCircularSubarray().maxSubarraySumCircular(new int[]{5,-3,5}));
        System.out.println(new MaximumSumCircularSubarray().maxSubarraySumCircular(new int[]{-3,-2,-3}));
    }
}
