package real_world_examples.netflix;

/*
* Let’s pretend you’re a developer on the Netflix engineering team. You are working on improving the user experience in finding content to watch.
* This involves the improvement of the search as well as recommendation functionality.

Features
We will need to introduce the following features to implement the improvements discussed above:

Feature # 1: We want to enable users to see relevant search results despite minor typos.
* */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupSimilarTitles {
    public static List<List<String>> groupTitles(String[] strs){
        if (strs.length == 0)
            return new ArrayList<>();

        Map<String, List<String>> res = new HashMap<>();

        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()){
                int index = c - 'a';
                count[index]++;
            }

            StringBuilder delimStr = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                delimStr.append('#');
                delimStr.append(count[i]);
            }

            String key = delimStr.toString();
            if (!res.containsKey(key))
                res.put(key, new ArrayList<String>());

            res.get(key).add(s);
        }

        return new ArrayList<List<String>>(res.values());
    }

    public static void main(String[] args) {
        // Driver code
        String titles[] = {"duel","dule","speed","spede","deul","cars"};

        List<List<String>> gt = groupTitles(titles);
        String query = "spede";

        // Searching for all titles
        for (List<String> g : gt){
            if (g.contains(query))
                System.out.println(g);
        }
    }
}