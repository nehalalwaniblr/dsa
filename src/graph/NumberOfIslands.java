package graph;

public class NumberOfIslands {
//    NEED TO MEMORISE THE LOGIC
    public int numIslands(char[][] grid) {
        int result =0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
//                for each cell if its a water do a dfs for that cel finding all the neighbouring water cells
                if(grid[i][j]=='1'){
                    result++;
                    dfs(grid,i,j);
                }
            }
        }
        return result;
    }

    void dfs(char[][]grid,int r, int c){
        int rows=grid.length;
        int columns = grid[0].length;
        //directions = left,top,right,down
        int[][] directions = new int[][]{{0,-1},{-1,0},{0,1},{1,0}};
        if(r<0||r>=rows || c<0 || c>=columns || grid[r][c]=='0')
            return;
        grid[r][c]='0';
//        iterate over all the four directions; for each direction call dfs for that cell to visit all the neighbours

        for(int[] dir:directions){
            dfs(grid,r+dir[0],c+dir[1]);
        }
    }
}
