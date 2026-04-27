package leet_again.graphs;


import java.util.*;

class AlienDictionary {
    Map<Character, List<Character>> adjacencyList = new HashMap<>();
    Map<Character, Integer> indegrees = new HashMap<>();
    StringBuilder result = new StringBuilder();

    public String foreignDictionary(String[] words) {
        for (String word : words) {
            for (char c : word.toCharArray()) {
                adjacencyList.putIfAbsent(c, new ArrayList<>());
                indegrees.putIfAbsent(c, 0);
            }
        }
        String current = words[0];
        for (int i = 1; i < words.length; i++) {
            Character[] edge = getLexicoSmall(current, words[i]);
            if (edge != null && edge.length == 1) {
                return "";
            }
            if (edge != null && edge.length == 2) {
                Character u = edge[0];
                Character v = edge[1];
                List<Character> neighbours = adjacencyList.get(u);
                if (neighbours != null) {
                    neighbours.add(v);
                } else {
                    neighbours = new ArrayList<>();
                    neighbours.add(v);
                }
                adjacencyList.put(u, neighbours);
                adjacencyList.computeIfAbsent(v, k -> new ArrayList<>());

                indegrees.put(v, indegrees.getOrDefault(v, 0) + 1);
                indegrees.put(u,indegrees.getOrDefault(u, 0));
            }


            current = words[i];
        }
        Queue<Character> queue = new LinkedList<>();
        for (Map.Entry<Character, Integer> entry : indegrees.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        //iterate on queue
        while (!queue.isEmpty()) {
            Character c = queue.poll();
            result.append(c);
            for (Character ch : adjacencyList.get(c)) {
                if (indegrees.get(ch) != null) {
                    indegrees.put(ch, indegrees.get(ch) - 1);
                    if (indegrees.get(ch) == 0) {
                        queue.add(ch);
                    }
                }
            }
        }

        if (result.length() != adjacencyList.size())
            return "";
        return result.toString();
    }

    Character[] getLexicoSmall(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        if (word1.startsWith(word2) && word1.length() > word2.length()) {

            return new Character[]{'#'};
        }
        int i = 0;
        int j = 0;
        while (i < m && j < n && word1.charAt(i) == word2.charAt(j)) {
            i++;
            j++;
        }
        if (i < m && j < n) {
            return new Character[]{word1.charAt(i), word2.charAt(j)};
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new AlienDictionary().foreignDictionary(new String[]{"z","o"}));
        System.out.println(new AlienDictionary().foreignDictionary(new String[]{"hrn","hrf","er","enn","rfnn"}));
        System.out.println(new AlienDictionary().foreignDictionary(new String[]{"wrtkj,wrt"}));
        System.out.println(new AlienDictionary().foreignDictionary(new String[]{"z,z"}));
    }
}
