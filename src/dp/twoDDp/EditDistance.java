package dp.twoDDp;

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
    int m;
    int n;
    int dp[][];

    public int minDistance(String word1, String word2) {
        m = word1.length();
        n = word2.length();
        dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], -1);
        }
//        return solve(word1, word2, 0, 0);//1st approach
        return solve(word1, word2, m, n);//2 nd approach
    }
//Approach 1: start from i=0;j=0
    /*private int solve(String word1, String word2, int i, int j) {
        // if second string is exhausted then extra characters from first should be
        // removed
        if (j >= n)
            return m - i;
        // if first string is exhausted then extra characters in second should be added
        // to first to make it same as second
        if (i >= m)
            return n - j;
        if (dp[i][j] != -1)
            return dp[i][j];
        // if both the characters at the index are same; no need to perform any
        // operation simply move on
        if (word1.charAt(i) == word2.charAt(j))
            return dp[i][j] = solve(word1, word2, i + 1, j + 1);
        else {
            int insert = 1 + solve(word1, word2, i, j + 1);
            int delete = 1 + solve(word1, word2, i + 1, j);
            int replace = 1 + solve(word1, word2, i + 1, j + 1);
            return dp[i][j] = findMinOf3(insert, delete, replace);
        }

    }*/

    int findMinOf3(int n1, int n2, int n3) {
        if (n1 <= n2 && n1 <= n3)
            return n1;
        if (n2 <= n1 && n2 <= n3)
            return n2;
        return n3;
    }

    //        Approach 2: starting i=m;j=n

    /*private int solve(String word1, String word2, int m, int n) {
        // if both the characters at the index are same; no need to perform any
        // operation simply move on
        if (n == 0 || m == 0)
            return m + n;
        if (dp[m][n] != -1)
            return dp[m][n];
        if (word1.charAt(m - 1) == word2.charAt(n - 1))
            return dp[m][n] = solve(word1, word2, m - 1, n - 1);
        else {
            int insert = 1 + solve(word1, word2, m, n - 1);
            int delete = 1 + solve(word1, word2, m - 1, n);
            int replace = 1 + solve(word1, word2, m - 1, n - 1);
            return dp[m][n] = findMinOf3(insert, delete, replace);
        }
    }*/

    private int solve(String word1, String word2, int m, int n) {
       for(int i=0;i<=m;i++) {
           for (int j = 0; j <= n; j++) {
               if (n == 0 || m == 0)
                   dp[i][j] = i + j;
               if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                   dp[i][j] = dp[i - 1][j - 1];
               } else {
                   int insert = dp[i][j - 1];
                   int delete = dp[i - 1][j];
                   int replace = dp[i - 1][j - 1];
                   return dp[i][j] = 1 + findMinOf3(insert, delete, replace);
               }
           }
       }
       return dp[m][n];
    }

}
