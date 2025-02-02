import java.util.*;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String temp = sortString(strs[i]);
            if (!map.containsKey(temp)) {
                List<String> s = new ArrayList<>();
                s.add(strs[i]);
                map.put(temp, s);
            } else {
                List<String> strings = map.get(temp);
                strings.add(strs[i]);
            }
        }

        return new ArrayList<>(map.values());
    }

    String sortString(String s) {
        if (!s.isEmpty()) {
            char[] ar = s.toCharArray();
            Arrays.sort(ar);
            return String.valueOf(ar);
        }
        return "";
    }

    public static void main(String[] args) {
        new GroupAnagrams().groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"});
        new GroupAnagrams().groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"});

    }
}
