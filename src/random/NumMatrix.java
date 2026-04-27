package random;

import java.util.Arrays;

/*

1,3,2,4
2,3,1,2
3,6,2,8
* Given a 2D matrix matrix, handle multiple queries of the following type:

Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
Implement the NumMatrix class:

NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
You must design an algorithm where sumRegion works on O(1) time complexity.*/
class NumMatrix {
    int r;
    int c;
    int[][] prefix;
    int[][] input;
    public NumMatrix(int[][] matrix) {
        this.input = matrix;
        this.r = matrix.length;
        this.c = matrix[0].length;
        this.prefix = new int[r][c];
        for(int i = 0;i<r;i++){
            Arrays.fill(prefix[i],0 );
        }

        //prefix sum
        for(int i = 0;i<r;i++){
            for(int j=0;j<c;j++){
                if(j==0){
                    prefix[i][j] = input[i][j];
                }else{
                    prefix[i][j] = prefix[i][j-1]+ input[i][j];
                }
            }
        }

    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum=0;
        for(int i=row1;i<=row2;i++){
            if(col1-1>=0){
                sum += prefix[i][col2] - prefix[i][col1-1];
            }else{
                sum+=prefix[i][col2];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
//        System.out.println(new NumMatrix(new int[][]{{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}}).sumRegion(2,1,4,3));
//        System.out.println(new NumMatrix(new int[][]{{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}}).sumRegion(1,2,2,4));


        System.out.println(new NumMatrix(new int[][]{{-4,-5}}).sumRegion(0,0,0,0));
        System.out.println(new NumMatrix(new int[][]{{-4,-5}}).sumRegion(0,0,0,1));
        System.out.println(new NumMatrix(new int[][]{{-4,-5}}).sumRegion(0,1,0,1));



    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */