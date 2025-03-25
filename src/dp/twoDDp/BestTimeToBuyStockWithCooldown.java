package dp.twoDDp;

import java.util.Arrays;

/*
* You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:

After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).



Example 1:

Input: prices = [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]
Example 2:

Input: prices = [1]
Output: 0


Constraints:

1 <= prices.length <= 5000
0 <= prices[i] <= 1000
* */
public class BestTimeToBuyStockWithCooldown {
    int[][] dp;

    public int maxProfit(int[] prices) {
        dp = new int[prices.length + 1][2];
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }
        return solve(prices, 0, 1);
    }

    int solve(int[] prices, int day, int buy) {
        /*
        * Either you buy or you dont buy:
        *
        * if you buy then either you actually buy or not buy--if you actually buy then future you'll sell so profit would be solve-price[i]
        * else
        * i.e. you sell so you'll sell or actually not sell--if you sell then cool down would be there and profit would be current price
        * */
        if (day >= prices.length)
            return 0;
        int take;
        int notTake;
        if (dp[day][buy] != -1)
            return dp[day][buy];
        int result = 0;
        if (buy == 1) {
            take    = solve(prices, day + 1, 0) - prices[day];// buy-sell
            notTake = solve(prices, day + 1, 1);
        } else {
            take = prices[day] + solve(prices, day + 2, 1);// if we sell today we need a cool down of 1 day and then we
            // can buy
            notTake = solve(prices, day + 1, 0);// cant buy if we havent sold old stocks
        }
        result = Math.max(take, notTake);
        dp[day][buy] = result;
        return result;
    }
}
