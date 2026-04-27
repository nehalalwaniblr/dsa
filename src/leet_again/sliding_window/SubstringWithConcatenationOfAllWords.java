package leet_again.sliding_window;

import java.util.*;
/*Brute force
* 1. iterate over string; starting at each index get substring with staring at index i till i+ the max length of the string i.e. wordLength* total words
* 2. for each substring fetched create a map containign strings of length wordLength and value as its frequency
* (Before that maintain a map of strings from the given words)
* Now, check if the substring map freq and original map fre are same. if not return false else true and add to result the staring index i.e. i
*
*Current complexity:
O(N*M*P)
Suggested complexity:
O(N∗P)
Suggestions:
Adopt a sliding window approach to update word counts incrementally, eliminating the need to rebuild maps for every substring.
* */

public class SubstringWithConcatenationOfAllWords {
    /*public List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        int wordLength = words[0].length();
        int totalLength = words.length * words[0].length();
        int n = s.length();
        for (int i = 0; i <= n-totalLength; i++) {
            String chunk = s.substring(i, i+totalLength);
            if(isStringValid(chunk, wordLength, map)){
                result.add(i);
            }
        }

        return result;
    }

    private boolean isStringValid(String chunk, int wordLength, Map<String, Integer> map) {
        Map<String, Integer> chunkMap = new HashMap<>();
        for (int i = 0; i < chunk.length(); i = i + wordLength) {
            String str = chunk.substring(i, i+wordLength);
            chunkMap.put(str, chunkMap.getOrDefault(str, 0) + 1);
        }
        for (String s : map.keySet()){
            if(!chunkMap.containsKey(s) || !Objects.equals(chunkMap.get(s), map.get(s)))
                return false;
        }
        return true;
    }*/

    //optimized
    public List<Integer> findSubstring(String s, String[] words) {

        List<Integer> result = new ArrayList<>();
        if (s.length() == 0 || words.length == 0)
            return result;

        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        int wordLength = words[0].length();
        int totalWords = words.length;

        // try all offsets
        for (int k = 0; k < wordLength; k++) {

            int left = k, right = k;
            Map<String, Integer> windowMap = new HashMap<>();
            int count = 0;

            while (right + wordLength <= s.length()) {

                String word = s.substring(right, right + wordLength);
                right += wordLength;

                if (map.containsKey(word)) {

                    windowMap.put(word, windowMap.getOrDefault(word, 0) + 1);
                    count++;

                    // shrink if extra
                    while (windowMap.get(word) > map.get(word)) {
                        String leftWord = s.substring(left, left + wordLength);
                        windowMap.put(leftWord, windowMap.get(leftWord) - 1);
                        left += wordLength;
                        count--;
                    }

                    if (count == totalWords) {
                        result.add(left);
                    }

                } else {
                    windowMap.clear();
                    count = 0;
                    left = right;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new SubstringWithConcatenationOfAllWords().findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
        System.out.println(new SubstringWithConcatenationOfAllWords().findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "word"}));
        System.out.println(new SubstringWithConcatenationOfAllWords().findSubstring("barfoofoobarthefoobarman", new String[]{"bar", "foo", "the"}));
        System.out.println(new SubstringWithConcatenationOfAllWords().findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","good"}));
        System.out.println(new SubstringWithConcatenationOfAllWords().findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","good"}));



    }
}
