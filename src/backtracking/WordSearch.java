package backtracking;
/*Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.



Example 1:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true
Example 2:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true
Example 3:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false


Constraints:

m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.


Follow up: Could you use search pruning to make your solution faster with a larger board?*/
public class WordSearch {
    int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (find(board, word, m, n, i, j, 0))
                        return true;
                }
            }
        }
        return false;
    }

    boolean find(char[][] board, String word, int m, int n, int i, int j, int index) {
        if (index >= word.length())
            return true;
        if (i < 0 || j < 0 || i >= m || i >= n || board[i][j] != word.charAt(index) || board[i][j] == '$')
            return false;
        char temp = board[i][j];
        board[i][j] = '$';
        for (int[] direction : directions) {
            int newI = i + direction[0];
            int newJ = j + direction[1];
            if(find(board, word, m, n, newI, newJ, index + 1))
                return true;
        }
        board[i][j] = temp;
        return false;
    }
}
