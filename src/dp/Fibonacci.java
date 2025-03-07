package dp;

import java.util.Arrays;

public class Fibonacci {
    //   Recursion; O(2 to the power n)
    public int fibRecursion(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return solve(n, dp);
    }

    //    Bottom-up apparoach
    public int fibBottomUp(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        dp[0]=0;
        dp[1]=1;
        for(int i=2; i<n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    private int solve(int n, int[] dp) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        if (dp[n] != -1)
            return dp[n];

        return dp[n] = solve(n - 1, dp) + solve(n - 2, dp);
    }

    public  int fibOptimized(int n){
        int a =0;int b=1; int sum=0;
        for(int i=0;i<n;i++){
            sum=a+b;
            a=b;
            b=sum;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new Fibonacci().fibRecursion(5));
    }
}
