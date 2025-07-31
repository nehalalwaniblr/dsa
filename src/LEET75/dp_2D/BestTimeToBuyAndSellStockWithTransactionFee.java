package LEET75.dp_2D;

import java.util.Arrays;

/*You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.

Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.

Note:

You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
The transaction fee is only charged once for each stock purchase and sale.


Example 1:

Input: prices = [1,3,2,8,4,9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
- Buying at prices[0] = 1
- Selling at prices[3] = 8
- Buying at prices[4] = 4
- Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
Example 2:

Input: prices = [1,3,7,5,10,3], fee = 3
Output: 6


Constraints:

1 <= prices.length <= 5 * 104
1 <= prices[i] < 5 * 104
0 <= fee < 5 * 104*/
public class BestTimeToBuyAndSellStockWithTransactionFee {
    int[][] dp;
    public int maxProfit(int[] prices, int fee) {
        dp = new int[prices.length + 1][2];
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }
        return solve(prices,fee,0,1);
    }

    private int solve(int[] prices, int fee, int day, int buy) {
        if(day>=prices.length)
            return 0;
        int take;
        int notTake;
        if(dp[day][buy]!=-1)
            return dp[day][buy];
        if(buy==1){
            take = solve(prices,fee, day+1, 0)-prices[day];//I bought today so I'll sell tomorrow(solve(prices,fee, day+1, false)); SP- CP(prices[day])
            notTake = solve(prices,fee,day+1,1);
        }else{
            take = prices[day]+solve(prices,fee, day+1, 1)-fee;//I sold today so I got today's price+I'll make a cool down of a day(if applicable) and then will sell
            notTake = solve(prices,fee,day+1,0);
        }
        return dp[day][buy] = Math.max(take, notTake);
    }

    public static void main(String[] args) {
        System.out.println(new BestTimeToBuyAndSellStockWithTransactionFee().maxProfit(new int[]{1,3,2,8,4,9},2));
        System.out.println(new BestTimeToBuyAndSellStockWithTransactionFee().maxProfit(new int[]{1,3,7,5,10,3},3));
    }
}
