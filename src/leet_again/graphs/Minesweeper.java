package leet_again.graphs;

import java.util.Arrays;
/*https://leetcode.com/problems/minesweeper/description/*/
public class Minesweeper {
    int M;
    int N;

    public char[][] updateBoard(char[][] board, int[] click) {
        M = board.length;
        N = board[0].length;
        boolean[][] visited = new boolean[M][N];
        int x = click[0];
        int y = click[1];
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }
        dfs(board, x, y);
        return board;
    }

    /*If mines > 0 → put digit and stop
    Else → mark 'B' and expand*/
    private void dfs(char[][] board, int x, int y) {
        if (x < 0 || y < 0 || x >= M || y >= N || board[x][y] != 'E')
            return;
        int mines = countMines(board, x, y);
        if (mines > 0) {
            board[x][y] = (char) (mines + '0');
            return;
        }
        board[x][y] = 'B';
        //up
        if (y - 1 >= 0) {
            dfs(board, x, y - 1);
        }
        //down
        if (y + 1 < N) {
            dfs(board, x, y + 1);
        }
        //right
        if (x + 1 < M) {
            dfs(board, x + 1, y);
        }
        //left
        if (x - 1 >= 0) {
            dfs(board, x - 1, y);
        }
        //up-right diagonal
        if (y - 1 >= 0 && x + 1 < M) {
            dfs(board, x + 1, y - 1);
        }
        //up-left diagonal
        if (y - 1 >= 0 && x - 1 >= 0) {
            dfs(board, x - 1, y - 1);
        }
        //bottom-right diagonal
        if (y + 1 < N && x + 1 < M) {
            dfs(board, x + 1, y + 1);
        }
        //bottom left diagonal
        if (y + 1 < N && x - 1 >=0) {
            dfs(board, x - 1, y + 1);
        }
    }

    private int countMines(char[][] board, int x, int y) {
        int count = 0;
        //up
        if (y - 1 >= 0 && board[x][y-1]=='M') {
            count++;
        }
        //down
        if (y + 1 < N && board[x][y+1]=='M') {
            count++;
        }
        //right
        if (x + 1 < M &&  board[x+1][y]=='M') {
            count++;
        }
        //left
        if (x - 1 >= 0 && board[x-1][y]=='M') {
            count++;
        }
        //up-right diagonal
        if (y - 1 >= 0 && x + 1 < M  && board[x+1][y-1]=='M') {
            count++;
        }
        //up-left diagonal
        if (y - 1 >= 0 && x - 1 >= 0 && board[x-1][y-1]=='M') {
            count++;
        }
        //bottom-right diagonal
        if (y + 1 < N && x + 1 < M && board[x+1][y+1]=='M') {
            count++;
        }
        //bottom left diagonal
        if (y + 1 < N && x - 1 >=0  && board[x-1][y+1]=='M') {
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Minesweeper().updateBoard(new char[][]{{'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'M', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}}, new int[]{3, 0})));
    }
}
