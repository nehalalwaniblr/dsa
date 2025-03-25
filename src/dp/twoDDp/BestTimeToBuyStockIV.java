package dp.twoDDp;

import java.util.Arrays;

public class BestTimeToBuyStockIV {
    int[][][] dp;
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if(n <= 1)
            return 0;
        dp = new int[prices.length + 1][2][3];
        for (int[][] ints : dp) {
            for(int[] its: ints)
                Arrays.fill(its, -1);
        }
        return solve(prices, 0, 1,k);
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
