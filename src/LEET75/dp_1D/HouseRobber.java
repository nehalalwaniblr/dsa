package LEET75.dp_1D;

import java.util.Arrays;

/*You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.



Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.


Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 400
*/
public class HouseRobber {
    int[] dp;

    //recusrion + memoization
    public int rob(int[] nums) {
        dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return solve(nums, 0);
    }

    private int solve(int[] nums, int i) {
        if (i >= nums.length)
            return 0;
        if (dp[i] != -1)
            return dp[i];
        int steal = nums[i] + solve(nums, i + 2);
        int skip = solve(nums, i + 1);
        dp[i] = Math.max(steal, skip);
        return dp[i];
    }

    public int rob2(int[] nums) {
        int[] dp= new int[nums.length+1];
        Arrays.fill(dp,0);

        if(nums.length==1)
            return nums[0];
        if(nums.length==2)
            return Math.max(nums[0],nums[1]);
        dp[0]=0;
        dp[1]=nums[0];
        for(int i=2;i<=nums.length;i++){
            int steal = nums[i-1]+dp[i-2];
            int skip = dp[i-1];
            dp[i]=Math.max(steal,skip);
        }
        return dp[nums.length];
    }

    public static void main(String[] args) {
        System.out.println(new HouseRobber().rob2(new int[]{1, 2, 3, 1}));
        System.out.println(new HouseRobber().rob2(new int[]{2, 7, 9, 3, 1}));

    }
}
