package LEET75.array_and_strings;

import java.util.Arrays;

/*Given a string s, reverse only all the vowels in the string and return it.

The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.



Example 1:

Input: s = "IceCreAm"

Output: "AceCreIm"

Explanation:

The vowels in s are ['I', 'e', 'e', 'A']. On reversing the vowels, s becomes "AceCreIm".

Example 2:

Input: s = "leetcode"

Output: "leotcede"



Constraints:

1 <= s.length <= 3 * 105
s consist of printable ASCII characters.*/
public class ReverseVowelsOfString {
    public String reverseVowels(String s) {
        int i=0;int j=s.length()-1;
        char[] input = s.toCharArray();
        while (i<=j){
            if(input[i]=='a' || input[i]=='e' || input[i]=='i' || input[i]=='o' || input[i]=='u'
            ||input[i]=='A' || input[i]=='E' || input[i]=='I' || input[i]=='O' || input[i]=='U'){
                if(input[j]=='a' || input[j]=='e' || input[j]=='i' || input[j]=='o' || input[j]=='u'
                || input[j]=='A' || input[j]=='E' || input[j]=='I' || input[j]=='O' || input[j]=='U'){
                    char jChar = input[j];
                    char iChar = input[i];
                    input[i] = jChar;
                    input[j] = iChar;
                    i++;
                    j--;
                }else{
                    j--;
                }
            }else{
                i++;
            }
        }
        return String.valueOf(input);
    }

    public static void main(String[] args) {
        System.out.println(new ReverseVowelsOfString().reverseVowels("IceCreAm"));
        System.out.println(new ReverseVowelsOfString().reverseVowels("leetcode"));
//        System.out.println(new ReverseVowelsOfString().reverseVowels("IceCreAm"));
    }
}
