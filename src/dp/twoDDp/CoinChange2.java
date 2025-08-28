package dp.twoDDp;

import java.util.Arrays;

/*
* 518. Coin Change II
Medium

You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.

You may assume that you have an infinite number of each kind of coin.

The answer is guaranteed to fit into a signed 32-bit integer.



Example 1:

Input: amount = 5, coins = [1,2,5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
Example 2:

Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.
Example 3:

Input: amount = 10, coins = [10]
Output: 1


Constraints:

1 <= coins.length <= 300
1 <= coins[i] <= 5000
All the values of coins are unique.
0 <= amount <= 5000*/
public class CoinChange2 {
    int[][] dp ;
    public int change2(int amount, int[] coins) {
       dp = new int[coins.length+1][amount+1];
        for (int i=0;i<=coins.length;i++
             ) {
            Arrays.fill(dp[i],-1);
        }
        return solve(amount,coins, 0);
    }

//    Input: amount = 0, coins = [7]

    int solve(int amount, int[] coins, int i){
        if(dp[i][amount]!=-1)
            return dp[i][amount];
//        if index oob return 0
        if(i>=coins.length){
            dp[i][amount] = 0;
            return dp[i][amount];
        }
//        on each call we may/not reduce amount, once it begins 0 that is coins combinations are correct return 1
        if(amount==0){
            dp[i][amount] =1;
            return dp[i][amount];
        }

//        if amount is say 50Rs and 100 is denomination it cant be fulfilled hence move to next coin
        if(amount < coins[i]){
            return  dp[i][amount] = solve(amount,coins,i+1);
        }
//either you take or you skip;
// if you take reduce the amount and call the method with reduced amount and same coin as you can take the same coin infinite times
//if you skip, no change in amount, move to next coin
        int take = solve(amount-coins[i],coins,i);
        int skip = solve(amount,coins,i+1);
        return dp[i][amount]=take+skip;
    }

//    DP:

    public int change(int amount, int[] coins) {
        dp = new int[coins.length+1][amount+1];
        return solve(amount,coins, 0);
    }

    public static void main(String[] args) {
        System.out.println(new CoinChange2().change(5, new int[]{1,2,5}));
        System.out.println(new CoinChange2().change(3, new int[]{2}));
        System.out.println(new CoinChange2().change(10, new int[]{10}));

    }
}
