package dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.



Example 1:

Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false


Constraints:

1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.*/

//https://www.youtube.com/watch?v=oBUpyPZ08zU&list=PLpIkg8OmuX-L_QqcKB5abYynQbonaNcq3&index=53&ab_channel=codestorywithMIK
public class WordBreak {
    /*Break the word and search in dictionary;
     * Take index starting 0 till l=length of string
     *   if s.substring(0,l) is there in dictionary then call recursion for rest of the string
     * */
    /*Boolean[] possible ;

    public boolean wordBreak(String s, List<String> wordDict) {
        possible = new Boolean[s.length()];
            return solve(s,0,wordDict);
    }

    boolean solve(String s, int index, List<String> wordDict) {
        if (index >=s.length())
            return true;
//        if (wordDict.contains(s))
//            return true;
        if(possible[index]!=null)
            return true;
        for (int l = index+1; l <= s.length(); l++) {
            String temp = s.substring(index, l);

           if(wordDict.contains(temp) && solve(s,l,wordDict))
               return possible[index]= true;
        }
        return possible[index]=false;
    }*/

    Set<String> set;

    public boolean wordBreak2(String s, List<String> wordDict) {
        /*
        Intuition:
        * Break till one position and see if you find the word in dictionary; leetcode; break till l;not found;break till le;not forund; lee not found; leet-found, once found move to next index
        If youfind the word then move to next i.e. length of found word plus current index
        if you dont find the word keep extending your current index to find the word

         */
        set = new HashSet<>(wordDict);
        return solve(s, 0);

    }

    boolean solve(String s, int index) {
        if (index >= s.length()) {
            return true;
        }
        if (set.contains(s))
            return true;
        for (int i = index + 1; i <= s.length(); i++) {
            String temp = s.substring(index, i);
            if (set.contains(temp) && solve(s, i))
                return true;

        }
        return false;
    }
//leetcode
    public boolean wordBreak(String s, List<String> wordDict) {
        /*Intuition :
        * Here, we maintain a dp array which denotes till any position say i of string s does any word exists in dictionary
        * so first we will check for each character starting 0 till n
        * and for each character we see if the lsit of words we are iterating over from dictionary exist or not
        * if exist we set that in dp
        * if not we keep incrementing i
        * here, once a word is found we need to update our start pointer . why? coz the word is found we need to continue our search till end
        *
        * */
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;  // for empty string; it'll always exist
        for (int i = 1; i <= s.length(); i++) {
            for (String word : wordDict) {
                int start = i - word.length();
                if (start >= 0 && s.substring(start, i).equals(word) && dp[start]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        System.out.println(new WordBreak().wordBreak("leetcode", Arrays.asList("leet", "code")));
        System.out.println(new WordBreak().wordBreak("applepenapple", Arrays.asList("apple", "pen")));
        System.out.println(new WordBreak().wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
    }
}
