import java.util.HashMap;
import java.util.Map;

public class WordPattern {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        Map<Character,String> charToWord = new HashMap<>();
        Map<String,Character> wordToChar = new HashMap<>();

        for(int i=0;i<pattern.length();i++){
            if(charToWord.containsKey(pattern.charAt(i))){
                if(!charToWord.get(pattern.charAt(i)).equals(words[i])){
                    return false;
                }
            }else{
                charToWord.put(pattern.charAt(i), words[i]);
            }


            if(wordToChar.containsKey(words[i])){
                if(!wordToChar.get(words[i]).equals(pattern.charAt(i))){
                    return false;
                }
            }else{
                wordToChar.put(words[i],pattern.charAt(i));
            }
        }
        return true;

    }

    public static void main(String[] args) {
        System.out.println(new WordPattern().wordPattern("abba","dog cat cat dog"));
    }
}
