package leet_again.dp.twoDp;

import java.util.Arrays;

public class MinPathSum {
    int m;
    int n;

    public int minPathSum2(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        return solve(grid, 0, 0);
    }

    int solve(int[][] grid, int i, int j) {
        if (i == m - 1 && j == n - 1)
            return grid[i][j];
        if (i >= m || j >= n)
            return 0;
        //if at end of last col; move down
        if(j==n-1){
            return grid[i][j] + solve(grid, i + 1, j);
        } //if at end of last row; move right
         else if(i==m-1){
            return grid[i][j] + solve(grid, i, j + 1);
        }else{
            int right = grid[i][j] + solve(grid, i, j + 1);
            int down = grid[i][j] + solve(grid, i + 1, j);
            return Math.min(right, down);
        }
    }

    int[][] dp;
    public int minPathSum(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        dp = new int[m][n]; // this has to be m & n ; not m+1 and n+1; why? to keep it consistent with the grid
        dp[0][0] = grid[0][0];
        //fill first row as it is just addition of prev ele
        for(int i=1;i<m;i++){
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }

        //fill first column as it is just addition of prev col
        for(int i=1;i<n;i++){
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }

        //now fill remaining cells; how?
        //say you are at cell 1,1 how did you reach here? either from top or from left; so find min of both
        for(int i=1;i<m;i++){
            for(int j =1;j<n;j++){
                dp[i][j] = Math.min(grid[i][j]+dp[i-1][j], grid[i][j]+dp[i][j-1]);
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        System.out.println(new MinPathSum().minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2,1}}));
    }
}
