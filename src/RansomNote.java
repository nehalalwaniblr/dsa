import java.util.Arrays;

public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] chars= new int[26];
        for(int i=0;i<magazine.length();i++){
            chars[magazine.charAt(i)-97]+=1;
        }
        for(int i=0;i<ransomNote.length();i++){
            if(chars[ransomNote.charAt(i)-97]>0){
                chars[ransomNote.charAt(i)-97]-=1;
            }else{
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new RansomNote().canConstruct("aa","aab");
    }
}
