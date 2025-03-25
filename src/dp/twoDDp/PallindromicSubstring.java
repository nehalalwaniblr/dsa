package dp.twoDDp;

public class PallindromicSubstring {
    boolean[][] dp;

//   USing iterative approach
    public String longestPalindrome(String s) {
        int len = s.length();
        dp = new boolean[len + 1][len + 1];
        if (s.length() == 1)
            return s;
        String strResult = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String str = s.substring(i, j + 1);
                if (isPallindromicString(str,i,j)) {
                    if (str.length() > strResult.length()) {
                        strResult = str;
                    }
                }
            }
        }
        return strResult;
    }
    boolean isPallindrome(String s) {
        if (s.length() == 1)
            return true;
        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }

//    Using recursion + memoization
    public String longestPalindrome2(String s) {
        int len = s.length();
        dp = new boolean[len + 1][len + 1];
        if (s.length() == 1)
            return s;
        String strResult = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isPallindromicString(s,i,j)) {
                    if (j-i+1 > strResult.length()) {
                        strResult = s.substring(i,j+1);
                    }
                }
            }
        }
        return strResult;
    }

    boolean isPallindromicString(String s, int i, int j) {
        if (i >= j) {
            return dp[i][j] = true;
        }
        if (s.charAt(i) == s.charAt(j))
            return dp[i][j] = isPallindromicString(s, i+1, j-1);
        else
            return dp[i][j] = false;
    }


//Using bottom up approach
//    dp[i][j] = true means the given string starting i till j is a pallindrome; for every substring of length 1 would be pallindrome
//    Hence all diagonal ele will be 1
//public String longestPalindrome(String s) {
//    int len = s.length();
//    dp = new boolean[len + 1][len + 1];
//    for(int i=0;i<s.length();i++){
//        dp[i][i] = true;
//    }
//    for(int i =0;i<len;i++){
//
//    }
//}

    public static void main(String[] args) {
        System.out.println(new PallindromicSubstring().longestPalindrome2("babad"));
        System.out.println(new PallindromicSubstring().longestPalindrome2("cbbd"));
        System.out.println(new PallindromicSubstring().longestPalindrome2("ac"));
    }
}
