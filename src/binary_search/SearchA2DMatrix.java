package binary_search;

/*
* You are given an m x n integer matrix matrix with the following two properties:

Each row is sorted in non-decreasing order.
The first integer of each row is greater than the last integer of the previous row.
Given an integer target, return true if target is in matrix or false otherwise.

You must write a solution in O(log(m * n)) time complexity.*/
public class SearchA2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix==null)
            return false;
        int rows= matrix.length;
        int cols= matrix[0].length;
        int rowNumber = 0;
        for(int i=0;i<rows;i++){
            if(target >=matrix[i][0] &&  target<=matrix[i][cols-1]){
                rowNumber = i;
                break;
            }
        }
        for(int j=0;j<cols;j++){
            if(matrix[rowNumber][j]==target){
                return true;
            }
        }
        return false;

    }

    public static void main(String[] args) {
        System.out.println(new SearchA2DMatrix().searchMatrix(new int [][] {{1,3,5,7},{10,11,16,20},{23,30,34,60}},3));
        System.out.println(new SearchA2DMatrix().searchMatrix(new int [][] {{1,3,5,7},{10,11,16,20},{23,30,34,60}},13));

    }
}
