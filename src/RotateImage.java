public class RotateImage {
    public void rotate(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;
        for(int i= 0;i<m/2;i++){
            for(int j=0;j<n/2;j++){
                if(i==j)
                    continue;
                int temp=matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i]= temp;
            }
        }

        for(int i= 0;i<m;i++){
            for(int j=0;j<n;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
        for(int i=0;i<m/2;i++){
            int col=n-1;
            for(int j=0;j<n/2;j++){
                int temp = matrix[i][j];
                matrix[i][j]=matrix[i][col];
                matrix[i][col]= temp;
                col--;
            }
        }
        for(int i= 0;i<m;i++){
            for(int j=0;j<n;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }


    }

    public static void main(String[] args) {
        new RotateImage().rotate(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
    }
}
