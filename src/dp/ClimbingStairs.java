package dp;

import java.util.Arrays;
/*You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?



Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step


Constraints:

1 <= n <= 45
*/
public class ClimbingStairs {
    //    Brute force: construct a tree; consider n=3; so being at 3 you have tree like this
    /*
     *           3
     *       1       2
     *   -1    0   0     1
     *                -1     0
     *
     * See the no. of 0s are 3 hence there are 3 possibilities to climb 3 stairs
     *
     * Same approach with memoization below
     * */
    public int climbStairs(int n) {
        int[] dp = new int[46];
        Arrays.fill(dp, -1);
        return climbStairs2(n, dp);
    }

    public int climbStairs2(int n, int[] dp) {
        if (n < 0)
            return 0;
        if (n == 0)
            return 1;
        if (dp[n] != -1)
            return dp[n];
        return dp[n] = climbStairs2(n - 1, dp) + climbStairs2(n - 2, dp);

    }

    /*
     * Bottom up approach; we know to climb 1 stair there is only one way; to climb 2 stairs there are 2 ways-1 each and 2 in one go
     *
     * */

    // bottom up optimized
    public int climbStairsBottomUp(int n) {
        if(n==1 || n==2 || n==3)
            return n;
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        dp[0]=0;
        dp[1]= 1;
        dp[2]=2;
        for (int i=3;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

//    Further this can be optimized to use only 3 variables as in fibonacci series
    public static void main(String[] args) {
        System.out.println(new ClimbingStairs().climbStairs(45));
//        System.out.println(new ClimbingStairs().climbStairs(2));
    }
}
