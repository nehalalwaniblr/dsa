package leet_again.dp.twoDp;

public class UniquePathsSum2 {
    int m,n;
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        m= obstacleGrid.length;
        n = obstacleGrid[0].length;
        return solve(obstacleGrid, 0,0);
    }

    private int solve(int[][] obstacleGrid, int i, int j) {
        //destination reached return 1
        if(i==m-1 && j==n-1)
            return 1;
        if(obstacleGrid[i][j]==1 || i>=m ||j>=n)
            return 0;
        return solve(obstacleGrid,i+1,j)+solve(obstacleGrid,i,j+1);


    }


   //bottom up
    int[][] dp ;
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        m= obstacleGrid.length;
        n = obstacleGrid[0].length;
        dp = new int[m][n];
        dp[0][0] = obstacleGrid[0][0]==1?-1:0;
        //if first cell itself have obstacle return 0
        if(dp[0][0] == -1)
            return 0;
        //fill first row; if there is obstacle put -1
        for(int i =1;i<n;i++){
            if(dp[0][i-1]==-1) {
                dp[0][i] = -1;
            }else if(obstacleGrid[0][i]==1){
                dp[0][i] = -1;
            }else{
                dp[0][i] = 1;
            }
        }

        //fill first col; if there is obstacle put -1
        for(int i =1;i<m;i++){
            if(dp[i-1][0]==-1) {
                dp[i][0] = -1;
            }else if(obstacleGrid[i][0]==1){
                dp[i][0] = -1;
            }else{
                dp[i][0] = 1;
            }
        }

        //now iterate from i=1 and j=1;first row first col till end
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(dp[i-1][j]!=-1 && dp[i][j-1]!=-1){
                    dp[i][j] = dp[i-1][j]+dp[i][j-1];
                }else if(dp[i-1][j]!=-1){
                    dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }

}
