package dp;
/*
* You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

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
* */


import java.util.Arrays;

/*
* DP problems have 2 ways to be solved
* - Recursion+memoization(Top down): if you just use recursion as shown in rob method time limit will exceed for case [114,117,207,117,235,82,90,67,143,146,53,108,200,91,80,223,58,170,110,236,81,90,222,160,165,195,187,199,114,235,197,187,69,129,64,214,228,78,188,67,205,94,205,169,241,202,144,240]; hence you'll need to use memoization as well
* - Bottom up
*
* */
public class HouseRobber {
//    1st approach
    public int rob(int[] nums) {
        if(nums==null)
            return 0;
        if(nums.length==1)
            return nums[0];
        return solve(nums,0);

    }

    int solve(int[] nums,int i){
        if(i>=nums.length)
            return 0;
        int steal = nums[i]+solve(nums,i+2);
        int skip = solve(nums,i+1);
        return Math.max(steal,skip);
    }
//2nd approach

    public int rob2(int[] nums) {
        int[] dp = new int[101];
        Arrays.fill(dp,-1);
        if(nums==null)
            return 0;
        if(nums.length==1)
            return nums[0];
        return solve2(dp,nums,0);

    }

    int solve2(int[] dp, int[] nums,int i){
        if(i>=nums.length)
            return 0;
        if(dp[i]!=-1)
            return dp[i];
        int steal = nums[i]+solve2(dp, nums,i+2);
        int skip = solve2(dp, nums,i+1);
        return dp[i]=Math.max(steal,skip);
    }

//    Third approach
public int rob3(int[] nums) {
    int[] dp = new int[nums.length+1];
    if(nums.length==1)
        return nums[0];
    dp[0]=0;
    dp[1]=nums[1];
    for(int i=2;i<=nums.length;i++){
        int steal = nums[i-1]+dp[i-2];
        int skip = dp[i-1];
        dp[i]=Math.max(steal,skip);
    }
    return dp[nums.length];


}
    public static void main(String[] args) {
        System.out.println(new HouseRobber().rob(new int[]{1,2,3,1}));
        System.out.println(new HouseRobber().rob(new int[]{40,2,4,10}));

    }
}
