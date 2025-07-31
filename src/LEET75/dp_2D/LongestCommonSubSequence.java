package LEET75.dp_2D;

import java.util.Arrays;

/*Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.



Example 1:

Input: text1 = "abcde", text2 = "ace"
Output: 3
Explanation: The longest common subsequence is "ace" and its length is 3.
Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.


Constraints:

1 <= text1.length, text2.length <= 1000
text1 and text2 consist of only lowercase English characters.*/
public class LongestCommonSubSequence {

    //recursion+memoization
    int[][] dp;

    public int longestCommonSubsequence(String text1, String text2) {
        dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 0; i <= text1.length(); i++) {
            Arrays.fill(dp[i], -1);
        }
        return solve(text1, text2, 0, 0);
    }

    private int solve(String text1, String text2, int i, int j) {
        if (i > text1.length() - 1 || j > text2.length() - 1)
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];
        if (text1.charAt(i) == text2.charAt(j)) {
            return dp[i][j] = 1 + solve(text1, text2, i + 1, j + 1);
        } else {
            return dp[i][j] = Math.max(solve(text1, text2, i + 1, j), solve(text1, text2, i, j + 1));
        }
    }

    //    Bottom up approach
//    dp[i][j] means the longest common subseq between str1 and str2 of length i and j repsectively
    public int longestCommonSubsequenceDp(String text1, String text2) {
        int[][] dp =new int[text1.length()+1][text2.length()+1];
        for (int i = 0; i < text2.length(); i++) {
            dp[0][i] = 0;
        }

        for (int j = 0; j < text1.length(); j++) {
            dp[j][0] = 0;
        }

        for (int i = 1; i < text1.length()+1; i++) {
            for (int j = 1; j < text2.length()+1; j++) {
                if(text1.charAt(i-1)==text2.charAt(j-1)){
                    dp[i][j] = 1+dp[i-1][j-1];
                }else{
                    dp[i][j] =Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

    public static void main(String[] args) {
        System.out.println(new LongestCommonSubSequence().longestCommonSubsequence("abcde", "ace"));
        System.out.println(new LongestCommonSubSequence().longestCommonSubsequence("abc", "abc"));
        System.out.println(new LongestCommonSubSequence().longestCommonSubsequence("abc", "def"));

        System.out.println(new LongestCommonSubSequence().longestCommonSubsequenceDp("abcde", "ace"));
        System.out.println(new LongestCommonSubSequence().longestCommonSubsequenceDp("abc", "abc"));
        System.out.println(new LongestCommonSubSequence().longestCommonSubsequenceDp("abc", "def"));

    }

}
