package LEET75.dp_1D;

import java.util.Arrays;

public class MinCostClimbingStairs {
    /*You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.

You can either start from the step with index 0, or the step with index 1.

Return the minimum cost to reach the top of the floor.



Example 1:

Input: cost = [10,15,20]
Output: 15
Explanation: You will start at index 1.
- Pay 15 and climb two steps to reach the top.
The total cost is 15.
Example 2:

Input: cost = [1,100,1,1,1,100,1,1,100,1]
Output: 6
Explanation: You will start at index 0.
- Pay 1 and climb two steps to reach index 2.
- Pay 1 and climb two steps to reach index 4.
- Pay 1 and climb two steps to reach index 6.
- Pay 1 and climb one step to reach index 7.
- Pay 1 and climb two steps to reach index 9.
- Pay 1 and climb one step to reach the top.
The total cost is 6.


Constraints:

2 <= cost.length <= 1000
0 <= cost[i] <= 999*/
    /*Recursion & memoization*/
    int[] dp;

    public int minCostClimbingStairs2(int[] cost) {
        dp=new int[cost.length+1];
        Arrays.fill(dp,-1);
        return Math.min(solve(cost,0),solve(cost,1));
    }

    int solve(int[] cost, int stairNo ) {
        if (stairNo >= cost.length )
            return 0 ;
        if(dp[stairNo]!=-1)
            return dp[stairNo];
        dp[stairNo]= Math.min(cost[stairNo]+solve(cost, stairNo + 1), cost[stairNo]+solve(cost, stairNo + 2));
        return dp[stairNo];
    }

    //Bottom up approach
    public int minCostClimbingStairs(int[] cost) {
        if(cost.length==1)
            return cost[0];
        if(cost.length==2)
            return Math.min(cost[0],cost[1]);
        for(int i=2;i<cost.length;i++){
            cost[i]=Math.min(cost[i]+cost[i-1],cost[i]+cost[i-2]);
        }
        return Math.min(cost[cost.length-1],cost[cost.length-2]);
    }

    public static void main(String[] args) {
        System.out.println(new MinCostClimbingStairs().minCostClimbingStairs2(new int[]{10,15, 25}));
        System.out.println(new MinCostClimbingStairs().minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }
}
