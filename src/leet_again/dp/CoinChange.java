package leet_again.dp;

import java.util.Arrays;

public class CoinChange {
    /*Intuition:
    * 1. Lets iterate over given coins
    * 2. for each coin
    *       reduce amount by coin value and for rest of the amount call recursion
    * 3. if amount becomes 0 that means we have got the no.of coins; return 0
    * 4. when amount has gone below 0 that means when you were reducing amount by current coin(as in step 2) it went below 0 return -1 as it is not possible to make negative amount
    * 5. At each iteration get min of calculated result i.e. when prev result is larger than current then update result to use current result as we aim for min
    *
    * */
    public int coinChange2(int[] coins, int amount) {
        int result = Integer.MAX_VALUE;
        if (amount < 0)
            return -1;
        if (amount == 0)
            return 0;
        for (int coin : coins) {
            int res = coinChange(coins, amount - coin);
            if(res>=0 && res<result){
                result=res+1;
            }
        }
        if (result == Integer.MAX_VALUE)
            return -1;
        return result;
    }

    int[] mem ;
    public int coinChange(int[] coins, int amount) {
        mem = new int[amount+1];
        Arrays.fill(mem,-1);
        return solve(coins,amount);
    }

    int solve(int[] coins, int amount){
        int result = Integer.MAX_VALUE;
        if (amount < 0)
            return -1;
        if (amount == 0)
            return 0;
        if(mem[amount]!=-1){
            return mem[amount];
        }
        for (int coin : coins) {
            int res = solve(coins, amount - coin);
            if(res>=0 && res<result){
                result=res+1;
            }
        }
        if (result == Integer.MAX_VALUE)
            return mem[amount] = -1;
        return mem[amount] = result;
    }
    public static void main(String[] args) {
        System.out.println(new CoinChange().coinChange(new int[]{1, 2, 5}, 11));
        System.out.println(new CoinChange().coinChange(new int[]{2}, 3));
        System.out.println(new CoinChange().coinChange(new int[]{1}, 0));
    }
}
