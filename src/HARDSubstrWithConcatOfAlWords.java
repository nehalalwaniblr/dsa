import java.util.*;

public class HARDSubstrWithConcatOfAlWords {
    public List<Integer> findSubstring(String s, String[] words) {
        int len = words.length>0?words[0].length():0;
        Set<String> set = new HashSet<>();
        set.addAll(List.of(words));
        List<Integer> result = new ArrayList<>();
        Map<String, Integer> map = fillMap( words);
        int left = 0;
        int right = 0;
        for (left = 0; left < s.length()-len; left = left + len) {
            right = left;
            String substr = s.substring(left, left+len);
            if(s.length()-left<set.size()*len)
                return result;
            while (map.containsKey(substr) && map.get(substr)>0) {
                map.put(substr,map.get(substr)-1);
                right += len;
                if (right - left == set.size() * len){
                    result.add(left);
                    break;
                }
                if(right+len<=s.length()){
                    substr = s.substring(right, right+len);
                }
            }
            map = fillMap(words);
        }
        return result;
    }

    Map<String, Integer>  fillMap(String[] words) {
        Map<String, Integer> map = new HashMap<>();

        for (String word : words) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }

        }
        return map;
    }


    public static void main(String[] args) {
        System.out.println(new HARDSubstrWithConcatOfAlWords().findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","word"}));
    }
}
