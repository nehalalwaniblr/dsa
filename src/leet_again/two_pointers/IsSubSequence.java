package leet_again.two_pointers;

/*
Take two pointers sPtr and tPtr starting 0 for both strings s and t respectively
Iterate till the end of string for both the strings
if the characters match increment sPtr and tPtr by 1
if they dont just increment tPtr
After the loop ends if tptr is exhausted the length and sptr hasn't then return false else true
*/

public class IsSubSequence {
    public boolean isSubsequence(String s, String t) {
        int sPtr = 0;
        int tPtr =0;
        if(t.length()<s.length())
            return false;
        while(sPtr<s.length() && tPtr<t.length()){
            if(s.charAt(sPtr) == t.charAt(tPtr)){
                sPtr++;
                tPtr++;
            }else{
                tPtr++;
            }
        }
        if(tPtr>=t.length() && sPtr<s.length())
            return false;
        else
            return true;
    }

    public static void main(String[] args) {
        System.out.println(new IsSubSequence().isSubsequence("abc","ahbgdc"));
        System.out.println(new IsSubSequence().isSubsequence("axc", "ahbgdc"));
    }
}
