package leet_again.two_pointers;

//We'll take 2 ptrs start and end and start iterating till start<=end.
// In this we need to skip non-numeric characters. Also, non-number can be many in a sequence so we need a while loop instead of if in line 11
//At any point of the value at start and end ptr aren't same we'll skip
//At end retunr true

public class ValidPallindrome {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        char[] chars = s.toCharArray();
        int start = 0;
        int end = chars.length;
        while (start <= end) {
            while (start<end && (chars[start] < 48 || (chars[start] > 57 && chars[start] < 97) || chars[start] > 122)) {
                start++;
            }

            while (end>0 && (chars[end - 1] < 48 || (chars[end - 1] > 57 && chars[end - 1] < 97) || chars[end - 1] > 122))
                end--;
            if (start <= end && chars[start] != chars[end - 1])
                return false;
            start++;
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new ValidPallindrome().isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(new ValidPallindrome().isPalindrome("race a car"));
        System.out.println(new ValidPallindrome().isPalindrome(" "));

    }
}
