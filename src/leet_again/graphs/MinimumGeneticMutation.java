package leet_again.graphs;

import java.util.*;

/*
We need to bfs here
Take the source string , put in queue and perform dfs
for each entry fetched from queue - level wise check by changing each character with A,C,G,T one a t a time -
note can't use replace method it'll replace all matching characters; also you cak skip the replacement if its the same character

For every changed string check if it exists in word bank. if so add to queue;

At every pop from queue check if it has matched the destination string, if so return that string.

* Space complexity O(N*M*4); N= lenght of source; M = size of bank
* Time complexity: worst case -
* */
public class MinimumGeneticMutation {
    public int minMutation(String startGene, String endGene, String[] bank) {
        Set<String> visited = new HashSet<>();

        char[] characters = new char[]{'A', 'C', 'G', 'T'};
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));

        Queue<String> queue = new LinkedList<>();
        int count = 0;
        queue.add(startGene);
        visited.add(startGene);
        while (!queue.isEmpty()) {
            int n = queue.size();
            while (n > 0) {
                String str = queue.poll();
                if (str.equals(endGene))
                    return count;
                char[] charArray = str.toCharArray();
                for (int j = 0; j < charArray.length; j++) {
                    char original = charArray[j];
                    for (char character : characters) {
                        if(original==character) continue;
                        charArray[j] = character;
                        String newString = new String(charArray);
                        if (bankSet.contains(newString) && !visited.contains(newString)) {
                            queue.add(newString);
                            visited.add(newString);
                        }
                    }
                    charArray[j] = original;
                }
                n--;
            }
            count++;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumGeneticMutation().minMutation("AACCGGTT", "AACCGGTA", new String[]{"AACCGGTA"}));
        System.out.println(new MinimumGeneticMutation().minMutation("AACCGGTT", "AAACGGTA", new String[]{"AACCGGTA", "AACCGCTA", "AAACGGTA"}));

    }
}
