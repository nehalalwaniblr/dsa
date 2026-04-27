package leet_again.sliding_window;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*Given a string s, find the length of the longest substring without duplicate characters.



Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.


Constraints:

0 <= s.length <= 5 * 104
s consists of English letters, digits, symbols and spaces.
*/
public class LongestSubstringWithoutRepeatingCharacter {


    /*Brute force:
     * use 2 loops : for each character start and check if it exists in set; if so break the inner loop
     * Continue moving to next char till not found in set of end of string
     * Notice to start the set on each new start
     * */
    public int lengthOfLongestSubstring2(String s) {
        if (s.isEmpty())
            return 0;
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < s.length(); i++) {
            Set<Character> set = new HashSet<>();
            for (int j = i; j < s.length(); j++) {
                if (set.contains(s.charAt(j))) {
                    break;
                }
                set.add(s.charAt(j));
                result = Math.max(result, j - i + 1);
            }
        }
        return result;
    }

    /*Using sliding window approach
    * Here, start with i and j and calculate length, put in map the char and its index and increment j
    * At any point of the character already appeared then check if the appearance is before or after ith position
    * coz if the index appears before i then we dont need to shift i; why-see e.g. cadbzabcd; in this case window will shift for both
    * a abd b when they repeat but not for c coz when j = 7 (at c from last); i would be at 4; window will be 4 to 7 as making it 0 to 7 is incorrect it will have duplicates
    * if it appears after ith position then we need to shift i to index+1 to make a new window
    *
    * */
    public int lengthOfLongestSubstring(String s) {
        int i = 0;
        int j = 0;
        int len = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (j < s.length()) {
            if (map.containsKey(s.charAt(j))) {
                int index = map.get(s.charAt(j)); //if the character appears before point i to next of that index as the duplicate has to be ignored
                if (i <= index) {// this has to happen only when i is before the place of duplicate; if it is after we dont want to iterate again we have already checked the part
                    i = index + 1;

                }
            }
            map.put(s.charAt(j), j);
            len = Math.max(j - i + 1, len);
            j++;
        }
        return len;
    }


    public static void main(String[] args) {
        System.out.println(new LongestSubstringWithoutRepeatingCharacter().lengthOfLongestSubstring("abcabcbb"));
        System.out.println(new LongestSubstringWithoutRepeatingCharacter().lengthOfLongestSubstring("bbbbb"));
        System.out.println(new LongestSubstringWithoutRepeatingCharacter().lengthOfLongestSubstring("pwwkew"));
        System.out.println(new LongestSubstringWithoutRepeatingCharacter().lengthOfLongestSubstring("cadbzabcd"));
        System.out.println(new LongestSubstringWithoutRepeatingCharacter().lengthOfLongestSubstring("tmzuxt"));
        System.out.println(new LongestSubstringWithoutRepeatingCharacter().lengthOfLongestSubstring("tmmzuxt"));





    }
}
