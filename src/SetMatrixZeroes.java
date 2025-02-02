public class SetMatrixZeroes {
//    public void setZeroes(int[][] matrix) {
//        int m= matrix.length;
//        int n = matrix[0].length;
//        boolean rowCol=false;
//        for(int i=0;i<m;i++){
//            for(int j=0;j<n;j++){
//                if(matrix[i][j]==0){
//                    if(matrix[i][0]==Integer.MAX_VALUE || matrix[i][0]==Integer.MIN_VALUE
//                    || matrix[0][j]==Integer.MAX_VALUE || matrix[0][j]==Integer.MIN_VALUE){
//                        rowCol=true;
//                    }
//                    matrix[i][0]=Integer.MAX_VALUE;
//                    matrix[0][j]=Integer.MIN_VALUE;
//                }
//            }
//        }
//        for(int i=0;i<m;i++) {
//            if (matrix[i][0] == Integer.MAX_VALUE || matrix[i][0] == Integer.MIN_VALUE) {
//                for (int col = 0; col < n; col++) {
//                        if(!((matrix[i][col]==Integer.MAX_VALUE || matrix[i][col]==Integer.MIN_VALUE)&& rowCol)){
//                            matrix[i][col] = 0;
//                        }
//                }
//            }
//        }
//
//        for(int i=0;i<n;i++){
//            if(matrix[0][i]==Integer.MIN_VALUE || matrix[0][i]==Integer.MAX_VALUE){
//                for(int row=0;row<m;row++){
////                    if(matrix[row][i]!=0){
//                        matrix[row][i]=0;
////                    }
//                }
//            }
//        }
//
//        for(int i=0;i<m;i++){
//            for(int j=0;j<n;j++) {
//                System.out.print(matrix[i][j]+" ");
//            }
//            System.out.println();
//            }
//    }
public void setZeroes(int[][] matrix) {
    boolean fr = false,fc = false;
    for(int i = 0; i < matrix.length; i++) {
        for(int j = 0; j < matrix[0].length; j++) {
            if(matrix[i][j] == 0) {
                if(i == 0) fr = true;
                if(j == 0) fc = true;
                matrix[0][j] = 0;
                matrix[i][0] = 0;
            }
        }
    }
    for(int i = 1; i < matrix.length; i++) {
        for(int j = 1; j < matrix[0].length; j++) {
            if(matrix[i][0] == 0 || matrix[0][j] == 0) {
                matrix[i][j] = 0;
            }}
    }
    if(fr) {
        for(int j = 0; j < matrix[0].length; j++) {
            matrix[0][j] = 0;
        }
    }
    if(fc) {
        for(int i = 0; i < matrix.length; i++) {
            matrix[i][0] = 0;
        }
    }
}
    public static void main(String[] args) {
        new SetMatrixZeroes().setZeroes(new int [][]{{0,1,2,0},{3,4,5,2},{1,3,1,5}});
//        new SetMatrixZeroes().setZeroes(new int [][]{{1,1,1},{1,0,1},{1,1,1}});
//        new SetMatrixZeroes().setZeroes(new int [][]{{0},{1}});

    }
}
