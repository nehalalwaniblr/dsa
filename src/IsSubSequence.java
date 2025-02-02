/*Given two strings s and t, return true if s is a subsequence of t, or false otherwise.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).



Example 1:

Input: s = "abc", t = "ahbgdc"
Output: true
Example 2:

Input: s = "axc", t = "ahbgdc"
Output: false


Constraints:

0 <= s.length <= 100
0 <= t.length <= 104
s and t consist only of lowercase English letters.
 */
public class IsSubSequence {
    public boolean isSubsequence(String s, String t) {
        if(s.isEmpty())
            return true;
        int i=0;
        if(s.length()>t.length())
            return false;
        int lastFoundIndex =-1;
        while(i<s.length()){
            if(!(t.indexOf(s.charAt(i), lastFoundIndex+1)> lastFoundIndex))
                return false;
            lastFoundIndex = t.indexOf(s.charAt(i), lastFoundIndex+1);
            i++;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new IsSubSequence().isSubsequence("aaaaaa","bbaaaa"));
    }
}
