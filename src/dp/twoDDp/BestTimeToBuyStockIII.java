package dp.twoDDp;

import java.util.Arrays;
/*You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete at most two transactions.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).



Example 1:

Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.


Constraints:

1 <= prices.length <= 105
0 <= prices[i] <= 105
*/
public class BestTimeToBuyStockIII {
    int[][][] dp;
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n <= 1)
            return 0;
        dp = new int[prices.length + 1][2][3];
        for (int[][] ints : dp) {
            for(int[] its: ints)
                Arrays.fill(its, -1);
        }
        return solve(prices, 0, 1,2);
    }

    int solve(int[] prices, int day, int buy, int k) {
        /*
         * Either you buy or you dont buy:
         *
         * if you buy then either you actually buy or not buy--if you actually buy then future you'll sell so profit would be solve-price[i]
         * else
         * i.e. you sell so you'll sell or actually not sell--if you sell then cool down would be there and profit would be current price
         * */
        if (day >= prices.length ||k==0)
            return 0;
        int take;
        int notTake;
        if (dp[day][buy][k] != -1)
            return dp[day][buy][k];
        int result = 0;
        if (buy == 1) {
            take    = solve(prices, day + 1, 0, k) - prices[day];// buy-sell
            notTake = solve(prices, day + 1, 1,k);
        } else {
            take = prices[day] + solve(prices, day + 1, 1, k-1);// if we sell today we need a cool down of 1 day and then we
            // can buy
            notTake = solve(prices, day + 1, 0,k);// cant buy if we havent sold old stocks
        }
        result = Math.max(take, notTake);
        dp[day][buy][k] = result;
        return result;
    }

    public static void main(String[] args) {
//        [2,1,2,0,1]
    }
}
