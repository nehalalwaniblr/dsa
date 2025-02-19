package graph;

/*You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:

Connect: A cell is connected to adjacent cells horizontally or vertically.
Region: To form a region connect every 'O' cell.
Surround: The region is surrounded with 'X' cells if you can connect the region with 'X' cells and none of the region cells are on the edge of the board.
To capture a surrounded region, replace all 'O's with 'X's in-place within the original board. You do not need to return anything.



Example 1:

Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]

Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]

*/
public class SurroundedRegions {
    boolean seen = false;

    public void solve(char[][] board) {
        if (board == null)
            return;
        if (board.length == 1)
            return;
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (board[i][j] == 'O' && !visited[i][j]) {
                    seen = false;
                    dfs(board, i, j, visited);
                    if (seen == false) {
                        mark(board, i, j, rows, cols);
                    }
                    seen = true;
                }
            }
        }
        return;
    }

    void mark(char[][] board, int i, int j, int r, int c) {
        if (i < 0 || i > r - 1 || j < 0 || j > c - 1)
            return;
        if (board[i][j] == 'X')
            return;

        board[i][j] = 'X';
        mark(board, i - 1, j, r, c);
        mark(board, i + 1, j, r, c);
        mark(board, i, j - 1, r, c);
        mark(board, i, j + 1, r, c);
    }

    void dfs(char[][] board, int r, int c, boolean[][] visited) {
        int rows = board.length;
        int cols = board[0].length;
        int[][] directions = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        if (r < 0 || r > rows - 1 || c < 0 || c > cols - 1)
            return;
        if (board[r][c] == 'X' || visited[r][c])
            return;
        if (r <= 0 || r >= rows - 1 || c <= 0 || c >= cols - 1)
            seen = true;
        visited[r][c] = true;
        for (int[] dir : directions) {
            dfs(board, r + dir[0], c + dir[1], visited);
        }
    }

    //    2nd approach; iterate first & last row and first and last column; mark all 0's and its regions as 1;
//    Iterate once again mark all 0s to X and 1s to back X
    public void solve2(char[][] board) {
        if (board == null)
            return;
        if (board.length == 1)
            return;
        int rows = board.length;
        int cols = board[0].length;
//    first & last row
        for (int i = 0; i < cols; i++) {
            dfs2(board, 0, i);
            dfs2(board, rows - 1, i);
        }
        for (int i = 0; i < rows ; i++) {
            dfs2(board, i, 0);
            dfs2(board, i, cols- 1);
        }
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if(board[i][j]=='1')
                    board[i][j]='O';
                else if (board[i][j]=='O') {
                    board[i][j]='X';

                }
            }
        }
        System.out.println(board);
    }

    void dfs2(char[][] board, int r, int c){
        if(board[r][c]=='0') {
            board[r][c] = '1';
            if (r + 1 < board.length) dfs2(board, r + 1, c);
            if (r > 1) dfs2(board, r - 1, c);
            if (c + 1 < board[r].length) dfs2(board, r, c + 1);
            if (c > 1) dfs2(board, r, c - 1);
        }
    }
    public static void main(String[] args) {
        new SurroundedRegions().solve2(new char[][]{{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}});
    }
}
