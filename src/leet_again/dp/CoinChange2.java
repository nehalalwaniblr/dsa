package leet_again.dp;

import java.util.Arrays;

public class CoinChange2 {
    int[][] mem ;
    public int change(int amount, int[] coins) {
        mem = new int[coins.length+1][amount+1];
        for(int a[]: mem)
            Arrays.fill(a, -1);
        int result =  ways(amount, 0, coins);
        return result;
    }
    /**
     Either skip or take the coin
     on skip simply increment i by 1 and call recursion
     on take reduce amount and keep i as it is and call recursion
     */
    int ways(int amount, int i, int[] coins) {
        if (i >= coins.length)
            return 0;
        if (amount < 0)
            return 0;
        if (amount == 0) {
            return 1;
        }
        if(mem[i][amount]!=-1)
            return mem[i][amount];
        if (amount < coins[i])
            ways(amount, i + 1, coins);
        //take
        int take = ways(amount - coins[i], i, coins);

        //skip
        int skip = ways(amount, i + 1, coins);

        return mem[i][amount] = take + skip;

    }

    public static void main(String[] args) {
        System.out.println(new CoinChange2().change(3, new int[]{1,2,5}));
    }
}
