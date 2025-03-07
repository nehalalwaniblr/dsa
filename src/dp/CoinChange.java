package dp;

import java.util.Arrays;

public class CoinChange {
    int t[][];

    public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE-1);
        dp[0]=0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins
            ) {
                if (coin <= i) {
                    dp[i] = Math.min(1 + dp[i - coin], dp[i]);
                }
            }
        }
        return dp[amount]==Integer.MAX_VALUE-1?-1:dp[amount];
    }
    /*Failing for few TCs like new int[]{2,5,10,1},27*/

    public int coinChange(int[] coins, int amount) {
        t = new int[13][10001];
        for (int i = 0; i < t.length; i++) {
            Arrays.fill(t[i], -1);
        }
        return minCoinsToGetAmount_Memoized(coins, amount, coins.length);
    }

    int minCoinsToGetAmount_Memoized(int[] coins, int amount, int n) {
        if (t[n][amount] != -1)
            return t[n][amount];

        //if there is no coin, then may be if I take infinite coins, I can reach to amount
        if (n == 0)
            return t[n][amount] = Integer.MAX_VALUE - 1;

        //if amount is 0, minimum coins to get 0 is 0
        if (amount == 0)
            return t[n][amount] = 0;

        //if there is only one coin, then we can reach to amount only if it's divisible
        //by that one coin value. Else it's not possible (hence returing invalid INT_MAX-1)
        if (n == 1) {
            if (amount % coins[n - 1] == 0)
                return t[n][amount] = amount / coins[n - 1];
            else
                return t[n][amount] = Integer.MAX_VALUE - 1;
        }

        if (coins[n - 1] <= amount)
            return t[n][amount] = Math.min(1 + minCoinsToGetAmount_Memoized(coins, amount - coins[n - 1], n), //unbounded knapsack
                    minCoinsToGetAmount_Memoized(coins, amount, n - 1));
        else
            return t[n][amount] = minCoinsToGetAmount_Memoized(coins, amount, n - 1);
    }

    public static void main(String[] args) {
        System.out.println(new CoinChange().coinChange2(new int[]{2}, 3));
    }
}
