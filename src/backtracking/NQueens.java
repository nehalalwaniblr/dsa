package backtracking;

import java.util.ArrayList;
import java.util.List;

/*The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.



Example 1:


Input: n = 4
Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
Example 2:

Input: n = 1
Output: [["Q"]]


Constraints:

1 <= n <= 9
*/
public class NQueens {
    List<List<String>> result;

    public List<List<String>> solveNQueens(int n) {
        result = new ArrayList<>(n);
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            /* replacement for
            for(int j=0;j<n;j++){
                b.append(".");
            }*/
            board.add(".".repeat(n));
        }
        solve(board, 0, n);
        return result;
    }

    void solve(List<String> board, int row, int n) {
        if (row >= n) {
            List<String> board2=new ArrayList<>(board);
            result.add(board2);
            return;

        }
        for (int col = 0; col < n; col++) {
            if (isValid(board, row, col)) {
                StringBuilder s = new StringBuilder(board.get(row));

                s.setCharAt(col, 'Q');
                board.set(row, s.toString());
//                replacement of above 3 lines while using char array - board[row][col]='Q';
                solve(board, row + 1, n);

                s.setCharAt(col, '.');
                board.set(row, s.toString());
            }
        }
    }

    boolean isValid(List<String> board, int row, int col) {
        // Look for up
        for (int i = row; i >= 0; i--) {
            if (board.get(i).charAt(col) == 'Q')
                return false;
        }

        // Check left diagonal upwards
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board.get(i).charAt(j) == 'Q')
                return false;
        }

        // Check right diagonal upwards
        for (int i = row, j = col; i >= 0 && j < board.size(); i--, j++) {
            if (board.get(i).charAt(j) == 'Q')
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new NQueens().solveNQueens(4));
    }
}
