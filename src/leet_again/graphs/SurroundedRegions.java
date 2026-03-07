package leet_again.graphs;

import java.util.Arrays;
import java.util.Stack;

/*
 * Start with boundary 0s and mark all the connected O's that they cant be converted to X
 * Convert the rest Xs to 0s
 * */
public class SurroundedRegions {

    int M;
    int N;

    public void solve(char[][] board) {
        M = board.length;
        N = board[0].length;
        //iterate first and last row
        /*this
        for (int i = 0; i < N; i++) {
            if (board[0][i] == 'O')
                dfsToMarkAllConnectedOsAs1s(board, 0, i);
        }

        for (int i = 0; i < N; i++) {
            if (board[M - 1][i] == 'O')
                dfsToMarkAllConnectedOsAs1s(board, M - 1, i);
        }
        //iterate first and last col
        for (int i = 0; i < M; i++) {
            if (board[i][0] == 'O')
                dfsToMarkAllConnectedOsAs1s(board, i, 0);
        }
        for (int i = 0; i < M; i++) {
            if (board[i][N-1] == 'O')
                dfsToMarkAllConnectedOsAs1s(board, i, N - 1);
        }*/

        //OR THIS
        // First and last row
        for (int j = 0; j < N; j++) {
            if (board[0][j] == 'O')
                dfsToMarkAllConnectedOsAs1s(board, 0, j);

            if (board[M - 1][j] == 'O')
                dfsToMarkAllConnectedOsAs1s(board, M - 1, j);
        }

        // First and last column
        for (int i = 0; i < M; i++) {
            if (board[i][0] == 'O')
                dfsToMarkAllConnectedOsAs1s(board, i, 0);

            if (board[i][N - 1] == 'O')
                dfsToMarkAllConnectedOsAs1s(board, i, N - 1);
        }

        //iterate again to mark all remaining O's to xs and 1s back to O's
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '1')
                    board[i][j] = 'O';
            }
        }
        System.out.println(Arrays.deepToString(board));
    }

    private void dfsToMarkAllConnectedOsAs1s(char[][] board, int i, int j) {
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(i, j));
        while (!stack.isEmpty()) {
            Pair currentNode = stack.pop();
            if (board[currentNode.x()][currentNode.y()] == 'O')
                board[currentNode.x()][currentNode.y()] = '1';

            //Move right
            if (currentNode.x() + 1 < M  && board[currentNode.x() + 1][currentNode.y()] == 'O') {
                stack.push(new Pair(currentNode.x() + 1, currentNode.y()));
//                board[currentNode.x() + 1][currentNode.y()] = 'X';
            }
            //Move left
            if (currentNode.x() - 1 >= 0 && board[currentNode.x() - 1][currentNode.y()] == 'O') {
                stack.push(new Pair(currentNode.x() - 1, currentNode.y()));
//                board[currentNode.x() - 1][currentNode.y()] = 'X';

            }
            //Move top
            if (currentNode.y() - 1 >= 0 && board[currentNode.x()][currentNode.y() - 1] == 'O') {
                stack.push(new Pair(currentNode.x(), currentNode.y() - 1));
//                board[currentNode.x()][currentNode.y() - 1] = 'X';

            }
            //Move bottom
            if (currentNode.y() + 1 < N  && board[currentNode.x()][currentNode.y() + 1] == 'O') {
                stack.push(new Pair(currentNode.x(), currentNode.y() + 1));
//                board[currentNode.x()][currentNode.y() + 1] = 'X';
            }

        }
    }

    public static void main(String[] args) {
        char[][] grid = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
//        char[][] grid = {
//                {'O','O','O'},
//                {'O','O','O'},
//                {'O','O','O'}
//        };


        new SurroundedRegions().solve(grid);
        System.out.println(Arrays.deepToString(grid));
    }
}
