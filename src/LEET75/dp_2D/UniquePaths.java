package LEET75.dp_2D;

import java.util.Arrays;

public class UniquePaths {
    /*There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 2 * 109.



Example 1:


Input: m = 3, n = 7
Output: 28
Example 2:

Input: m = 3, n = 2
Output: 3
Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down


Constraints:

1 <= m, n <= 100*/
    int[][] dp;

    public int uniquePaths(int m, int n) {
        dp = new int[m][n];
        for (int i = 0; i < m; i++)
            Arrays.fill(dp[i], -1);
        return solve(m, n, 0, 0);
    }

    private int solve(int m, int n, int i, int j) {
        if (i >= m - 1 && n >= j - 1)
            return 1;
        //out of bounds
        if (i >= m || j >= n)
            return 0;
        if(dp[i][j]!=-1)
            return dp[i][j];
        int right = solve(m, n, i, j + 1);
        int down = solve(m, n, i + 1, j);
        return dp[i][j]=right + down;
    }

    public static void main(String[] args) {
        System.out.println(new UniquePaths().uniquePaths(3, 7));
        System.out.println(new UniquePaths().uniquePaths(3, 2));
    }
}
