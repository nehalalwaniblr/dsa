package leet_again.graphs;

import java.util.*;

/*https://leetcode.com/problems/word-ladder/description/?envType=study-plan-v2&envId=top-interview-150
 * return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 *
 *
 * Solution:
 * Exactly same as MinimumGeneticMutation; only thing instead of iterating on just 4 chars A,C,G,T iterate over all english alphabets
 *
 *
 * N = number of words in wordList
L = word length

For each word we attempt:

L × 26 mutations

So:

Time = O(N × L × 26)
≈ O(N × L)
Space Complexity
O(N)

for visited + queue + set.
 * */
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> visited = new HashSet<>();

        Set<String> wordSet = new HashSet<>((wordList));
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        visited.add(beginWord);
        int currentCount = 1;
        while (!queue.isEmpty()) {
            int n = queue.size();
            while (n-- > 0) {
                String value = queue.poll();
                if (value.equals(endWord))
                    return currentCount;
                char[] chars = value.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char original = chars[i];
                    for (char j = 'a'; j <= 'z'; j++) {
                        if (original == j)
                            continue;
                        chars[i] = j;
                        String newString = new String(chars);
                        if (wordSet.contains(newString) && !visited.contains(newString)) {
                            queue.add(newString);
                            visited.add(newString);
                        }
                    }
                    chars[i] = original;
                }
            }
            currentCount++;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new WordLadder().ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
    }
}



