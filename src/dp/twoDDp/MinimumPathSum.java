package dp.twoDDp;

import java.util.Arrays;

/*Given a m x n grid filled with non-negative numbers,
find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
Example 1:
1,3,1
1,5,1
4,2,1



Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
Example 2:

Input: grid = [[1,2,3],[4,5,6]]
Output: 12


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 200
0 <= grid[i][j] <= 200

*/
public class MinimumPathSum {
    /*
     * First approach: recursion+Memoization top down
     * 1. Consider the cases that you cant move beyond the last column; and last row;
     * 2. Use recursion : if you reach last row; move right; if you reach last col move down; else min of down and right
     * 3. Use memoization; this makes O(m*N) and space as well
     *
     * Second approach: bottom up
     * 1,3,1
     * 1,5,1
     * 4,2,1
     *
     * dp:
     * dp[i][j] min path sum to reach [i][j] from[0][0]
     * 1,4
     * */
    int[][] dp;
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        dp = new int[rows][cols];
        for (int[] ints : dp) Arrays.fill(ints, -1);
        return solve(grid,0,0,rows,cols);
    }

    int solve(int[][] grid, int i, int j,int rows, int cols){
        if(i==rows-1 && j==cols-1)
            return dp[i][j]=grid[i][j];
        if(dp[i][j]!=-1)
            return dp[i][j];
        if(j==cols-1)
            return dp[i][j]=grid[i][j]+solve(grid, i+1,j,rows,cols);
        else if(i==rows-1)
            return dp[i][j]=grid[i][j]+solve(grid,i,j+1,rows,cols);
        else return dp[i][j]=Math.min((grid[i][j]+solve(grid,i,j+1,rows,cols)) ,(grid[i][j]+solve(grid,i+1,j,rows,cols)));
    }

    public int minPathSum2(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        dp = new int[rows][cols];
        dp[0][0] = grid[0][0];
        for(int i=1;i<cols;i++){
            dp[0][i] = grid[0][i]+dp[0][i-1];
        }
        for(int i=1;i<rows;i++){
            dp[i][0] = grid[i][0]+dp[i-1][0];
        }
        for(int i=1;i<rows;i++){
            for(int j=1;j<cols;j++){
                dp[i][j] = Math.min(grid[i][j]+dp[i-1][j],grid[i][j]+dp[i][j-1]);
            }
        }
        return dp[rows-1][cols-1];
    }

    public static void main(String[] args) {
        new MinimumPathSum().minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}});
    }
}
