package LEET75.dp_2D;

/*790. Domino and Tromino Tiling
Medium
Topics
Companies
You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.


Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large, return it modulo 109 + 7.

In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.



Example 1:


Input: n = 3
Output: 5
Explanation: The five different ways are show above.
Example 2:

Input: n = 1
Output: 1


Constraints:

1 <= n <= 1000
*/
public class DominoTromino {
//    f(n)=2*f(n-1)+f(n-3)
    int[] dp ;
    public int numTilings(int n) {
        final int M = 1000000007;
        dp=new int[n+1];
        dp[1] =1;
        dp[2]=2;
        dp[3]=5;
        for(int i=4;i<=n;i++){
            dp[i] = (2*dp[i-1]%M+dp[i-3]%M)%M;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new DominoTromino().numTilings(3));
        System.out.println(new DominoTromino().numTilings(4));
        System.out.println(new DominoTromino().numTilings(5));


    }
}
