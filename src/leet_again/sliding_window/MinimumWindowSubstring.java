package leet_again.sliding_window;

import java.util.HashMap;
import java.util.Map;

/*
* This solution uses a sliding window with two pointers (i, j) and a frequency map of characters in string t.
The map stores how many times each character is still required, and requiredCount tracks total remaining characters to match.
As j expands the window, matching characters reduce requiredCount and excess characters are tracked with negative counts.
Once all characters are matched (requiredCount == 0), the window shrinks from i to find the minimum valid substring.
During shrinking, removing a required character (map > 0) increases requiredCount, breaking the valid window.
Time Complexity: O(n), Space Complexity: O(k) where k is number of unique characters in t.
* */
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        int maxLength = Integer.MAX_VALUE;
        Map<Character, Integer> map = new HashMap<>();
        initializeMap(t, map);
        int i = 0;
        int j = 0;
        int startI = 0;
        int n = s.length();
        int requiredCount = t.length();
        while (j < n) {
            if (map.containsKey(s.charAt(j))) {
                if (map.get(s.charAt(j)) > 0)
                    requiredCount--;
                map.put(s.charAt(j), map.get(s.charAt(j)) - 1);
            }
            while (requiredCount == 0) {
                if (maxLength > j - i + 1) { //check the current length with max length
                    startI = i;
                    maxLength = j - i + 1;
                }
                if (map.containsKey(s.charAt(i))) {
                    map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
                    if (map.get(s.charAt(i)) > 0) {
                        requiredCount++;
                    }
                }
                i++;
            }
            j++;
        }
        return maxLength == Integer.MAX_VALUE ? "" : s.substring(startI, startI + maxLength);
    }

    private static void initializeMap(String t, Map<Character, Integer> map) {
        for (int i = 0; i < t.length(); i++) {
            if (map.containsKey(t.charAt(i))) {
                map.put(t.charAt(i), map.get(t.charAt(i)) + 1);
            } else {
                map.put(t.charAt(i), 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new MinimumWindowSubstring().minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(new MinimumWindowSubstring().minWindow("a", "a"));
        System.out.println(new MinimumWindowSubstring().minWindow("a", "aa"));
        System.out.println(new MinimumWindowSubstring().minWindow("aa", "a"));
        System.out.println(new MinimumWindowSubstring().minWindow("aa", "aa"));


    }
}
