package leet_again.dp.twoDp;

public class MaximalSquare {
    int m;
    int n;
//    int[][] mem;
    public int maximalSquare(char[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
//        mem =  new int[m][n];
//        for(int[] m: mem)
//            Arrays.fill(m,-1);
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    int res = solve(matrix,i, j);
                    result = Math.max(result, res);
                }
            }
        }
        return result*result;
    }

    int solve(char[][] matrix, int i, int j) {
        if (i >= m || j >= n) {
            return 0;
        }
        if(matrix[i][j]=='0'){
            return 0;
        }
//        if(mem[i][j] !=-1)
//            return mem[i][j];
        if (matrix[i][j] == '1') {
            int right = solve(matrix, i, j + 1);
            int down = solve(matrix, i + 1, j);
            int diagonal = solve(matrix, i + 1, j + 1);
            return 1+ Math.min(right, Math.min(down,diagonal));
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new MaximalSquare().maximalSquare(new char[][]{{'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        }));
    }
}
