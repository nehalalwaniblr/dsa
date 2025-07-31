package LEET75.sliding_window;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.

Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.



Example 1:

Input: s = "abciiidef", k = 3
Output: 3
Explanation: The substring "iii" contains 3 vowel letters.
Example 2:

Input: s = "aeiou", k = 2
Output: 2
Explanation: Any substring of length 2 contains 2 vowels.
Example 3:

Input: s = "leetcode", k = 3
Output: 2
Explanation: "lee", "eet" and "ode" contain 2 vowels.


Constraints:

1 <= s.length <= 105
s consists of lowercase English letters.
1 <= k <= s.length*/
public class MaximumNoOfVowels {
    Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    public int maxVowels(String s, int k) {
        int count = 0;
        int result = 0;
        //count vowels till window k
        for (int i = 0; i < k; i++) {
            if (vowels.contains(s.charAt(i)))
                count++;
        }
        result=count;
//Iterate  starting k and slide the window;check if vowel found at k then do +1; check if sliding the window before k was a vowel if yes do -1
        for (int i = k; i < s.length(); i++) {
            if (vowels.contains(s.charAt(i - k ))) {
                count--;
            }
            if (vowels.contains(s.charAt(i)))
                count++;
            result = Math.max(result, count);
        }
        return result;
    }

    public static void main(String[] args) {
//        System.out.println(new MaximumNoOfVowels().maxVowels("abciiidef",3));
//        System.out.println(new MaximumNoOfVowels().maxVowels("aeiou",2));
        System.out.println(new MaximumNoOfVowels().maxVowels("leetcode",3));

    }
}
