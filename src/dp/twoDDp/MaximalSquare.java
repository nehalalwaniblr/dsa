package dp.twoDDp;

import java.util.Arrays;

/*Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.



Example 1:


Input: matrix = [['1','0','1','0','0'],['1','0','1','1','1'],['1','1','1','1','1'],['1','0','0','1','0']]
Output: 4
Example 2:


Input: matrix = [['0','1'],['1','0']]
Output: 1
Example 3:

Input: matrix = [['0']]
Output: 0


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 300
matrix[i][j] is '0' or '1'.*/
public class MaximalSquare {
    /*
    * You can find a square if from any cell you move right ,you move diagonal and you move down. The max size of square would be min of all left, right and diagonal direction
    *
    * */
    int result = 0;
    int[][] dp;
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        dp=new int[m+1][n+1];
        for(int[] i: dp){
            Arrays.fill(i,-1);
        }
        for(int i=0;i<m;i++){
            for(int j =0;j<n;j++){
                if(matrix[i][j]=='1'){
                    int noOfSquares = solve(matrix, m, n, i,j);
                    result = Math.max(result,noOfSquares);
                }
            }
        }
        return result*result;
    }

    int solve(char[][] matrix, int m , int n , int i, int j){
        if(i>=m ||j>=n)
            return 0;
        if(matrix[i][j]=='0')
            return 0;
        if(dp[i][j]!=-1)
            return dp[i][j];
        int right = solve(matrix,m,n, i,j+1);
        int down = solve(matrix,m,n, i+1,j);
        int diagonal = solve(matrix,m,n, i+1,j+1);
        return dp[i][j] = 1+Math.min(right,Math.min(down,diagonal));
    }

    public int maximalSquare2(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        dp=new int[m+1][n+1];
        for(int i=0;i<m;i++){
            if(matrix[0][i]=='1')
                dp[0][i]=1;

            if(matrix[i][0]=='1')
                dp[i][0]=1;
        }
        for(int i=1;i<m;i++){
            for(int j =1;j<n;j++){
                if(matrix[i][j]=='1'){
                    dp[i][j] = 1+Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1]));
                    result = Math.max(dp[i][j],result);
                }
            }
        }
        return result*result;
    }

    public static void main(String[] args) {
        System.out.println(new MaximalSquare().maximalSquare(new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}}));
    }
}
