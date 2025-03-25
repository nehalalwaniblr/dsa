package dp.twoDDp;

/*Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.

An interleaving of two strings s and t is a configuration where s and t are divided into n and m substrings respectively, such that:

s = s1 + s2 + ... + sn
t = t1 + t2 + ... + tm
|n - m| <= 1
The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
Note: a + b is the concatenation of strings a and b.



Example 1:


Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Explanation: One way to obtain s3 is:
Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
Since s3 can be obtained by interleaving s1 and s2, we return true.
Example 2:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
Explanation: Notice how it is impossible to interleave s2 with any other string to obtain s3.
Example 3:

Input: s1 = "", s2 = "", s3 = ""
Output: true


Constraints:

0 <= s1.length, s2.length <= 100
0 <= s3.length <= 200
s1, s2, and s3 consist of lowercase English letters.*/
public class InterleavingStrings {
    /*
    * Start with 2 var i,j ; move i , j  as needed like : s1[i]==s[i+j]-->i+1 &i+j+1; s2[j]==s[i+j]-->j++;i+j+1
    * call recursively this with i, j  updated.
    * Base conditions:
    * 1. when all 3(i,j and i+j) pointers reach at the end that means all chars from both string are covered to form string 3
    * 2. when main string is exhausted and any one is not then return false as string isn't interleaved and contains all chars from one of the string only
    * 3. ow increase either i or j ; i+j will increase always
    * */
    int m;
    int n;
    int N;


    public boolean isInterleave(String s1, String s2, String s3) {
        m = s1.length();
        n = s2.length();
        N = s3.length();
        return solve(s1, s2, s3, 0, 0);
    }

    boolean solve(String s1, String s2, String s3, int i, int j) {
        boolean result=false ;
        if (i>=m && j >=n && i+j>=N)
            return true;
        if (i+j >= N)
            return false;
        if(i<m && s1.charAt(i)== s3.charAt(i+j))
            result = solve(s1,s2,s3,i+1,j);
        if(j<n && s2.charAt(j)==s3.charAt(i+j))
            result = solve(s1,s2,s3,i,j+1);
        return result;
    }
}
