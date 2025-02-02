import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {

    public static void main(String[] args) {
        System.out.println(romanToInt("III"));
        System.out.println(romanToInt("LVIII"));
        System.out.println(romanToInt("MCMXCIV"));
    }
    public static int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int result = 0;
        int i = s.length()-1;
        while (i >= 0) {
            Integer num = map.get(s.charAt(i));
            if (i - 1 >= 0 && num > map.get(s.charAt(i - 1))) {
                result += num - map.get(s.charAt(i - 1));
                i--;
            }else{
                result+=num;
            }
            i--;
        }
        return result;
    }
}
