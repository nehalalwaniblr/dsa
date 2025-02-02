import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if ((map.containsKey(s.charAt(i)) && map.get(s.charAt(i)) != t.charAt(i)) || (map.containsKey(t.charAt(i)) && map.get(t.charAt(i)) != s.charAt(i)))
                return false;
            else {
                map.put(s.charAt(i), t.charAt(i));
//                map.put(t.charAt(i), s.charAt(i));
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new IsomorphicStrings().isIsomorphic("egg", "add"));
        System.out.println(new IsomorphicStrings().isIsomorphic("foo", "bar"));
        System.out.println(new IsomorphicStrings().isIsomorphic("paper", "title"));
        System.out.println(new IsomorphicStrings().isIsomorphic("badc", "baba"));

    }
}
