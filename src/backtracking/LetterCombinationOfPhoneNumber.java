package backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationOfPhoneNumber {
    List<String> result = new ArrayList<>();
    Map<String, String> phone = new HashMap<>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};
    public List<String> letterCombinations(String digits) {
        if(digits.isEmpty())
            return result;
         solve(0,digits,"");
         return result;
    }

    void solve(int index, String input,String temp) {
        if(temp.length()>=input.length()){
            result.add(temp);
            return;
        }
        char ch = input.charAt(index);
        String s = phone.get(ch+"");
        for(int i=0;i<s.length();i++){
            temp= temp+s.charAt(i);
            solve(index+1,input,temp);
            if(!temp.isEmpty())
                 temp=temp.substring(0, temp.length()-1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new LetterCombinationOfPhoneNumber().letterCombinations("23"));
    }
}
