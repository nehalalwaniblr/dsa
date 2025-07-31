package LEET75.dp_2D;

import java.util.Arrays;

/*Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character


Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation:
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation:
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')


Constraints:

0 <= word1.length, word2.length <= 500
word1 and word2 consist of lowercase English letters.*/
public class EditDistance {
    int[][] dp ;
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        dp=new int[m][n];
        for (int i=0;i<m;i++){
            Arrays.fill(dp[i],-1);
        }
        return solve(word1, word2, 0, 0, m, n);

    }

    private int solve(String word1, String word2, int i, int j, int m, int n) {
        if (i >= m) {
//            //insert from word2
            return n - j;
        }
        if (j >= n) {
//            delete from word1
            return m - i;
        }
        if(dp[i][j]!=-1)
            return dp[i][j];
        if (word1.charAt(i) == word2.charAt(j)) {
            return dp[i][j]=solve(word1, word2, i + 1, j + 1, m, n);
        } else {
            int insert = 1 + solve(word1, word2, i, j + 1, m, n);
            int delete = 1 + solve(word1, word2, i + 1, j, m, n);
            int replace = 1 + solve(word1, word2, i + 1, j + 1, m, n);
            return dp[i][j]=findMin(insert, delete, replace);
        }
    }

    private int findMin(int insert, int delete, int replace) {
        if (insert <= delete && insert <= replace)
            return insert;
        else return Math.min(delete, replace);
    }

    public static void main(String[] args) {
        System.out.println(new EditDistance().minDistance("horse","ros"));
        System.out.println(new EditDistance().minDistance("intention","execution"));
    }
}
