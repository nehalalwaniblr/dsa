package dp.twoDDp;

import java.util.Arrays;

public class UniquePaths2 {
    int[][] dp;

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1)
            return 0;

        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;
        dp = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(dp[i], -1);
        }
        return solve(obstacleGrid, 0, 0, rows, cols);
    }

    int solve(int[][] obstacleGrid, int i, int j, int rows, int cols) {
        if (i > rows - 1 || j > cols - 1 || obstacleGrid[i][j] == 1)
            return dp[i][j]=0;
        if (i == rows - 1 && j == cols - 1)
            return dp[i][j]=1;
        if(dp[i][j]!=-1)
            return dp[i][j];
        return dp[i][j]=solve(obstacleGrid, i + 1, j, rows, cols) + solve(obstacleGrid, i, j + 1, rows, cols);
    }

    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;
        for (int i = 0; i < rows; i++) {
            Arrays.fill(dp[i], 0);
        }
        dp = new int[rows][cols];
        dp[0][0] = obstacleGrid[0][0];
        for (int i = 1; i < cols; i++) {
            if (obstacleGrid[0][i] == 1) {
                dp[0][i] = -1;
            } else if (dp[0][i - 1] == -1) {
                dp[0][i] = -1;
            } else {
                dp[0][i] = 1;
            }
        }

        for (int i = 1; i < rows; i++) {
            if (obstacleGrid[i][0] == 1) {
                dp[i][0] = -1;
            } else if (dp[i - 1][0] == -1) {
                dp[i][0] = -1;
            } else {
                dp[i][0] = 1;
            }
        }
        return dp[rows - 1][cols - 1];
    }


    public static void main(String[] args) {
        System.out.println(new UniquePaths2().uniquePathsWithObstacles(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}));
        System.out.println(new UniquePaths2().uniquePathsWithObstacles(new int[][]{{0, 1}, {0, 0}}));

    }
}
