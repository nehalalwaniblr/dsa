package LEET75.array_and_strings;

/*1071. Greatest Common Divisor of Strings
Easy
Topics
Companies
Hint
For two strings s and t, we say "t divides s" if and only if s = t + t + t + ... + t + t (i.e., t is concatenated with itself one or more times).

Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.



Example 1:

Input: str1 = "ABCABC", str2 = "ABC"
Output: "ABC"
Example 2:

Input: str1 = "ABABAB", str2 = "ABAB"
Output: "AB"
Example 3:

Input: str1 = "LEET", str2 = "CODE"
Output: ""


Constraints:

1 <= str1.length, str2.length <= 1000
str1 and str2 consist of English uppercase letters.*/

/*
* solution:
* 2. Euclidean Algorithm (Efficient Method)
The idea:

gcd(a, b) = gcd(b, a % b)
Keep replacing (a, b) with (b, a % b) until b = 0.
At that point, a is the GCD.
*
* BASICALLY, find gcd of 2 numbers(here length) then take substring of the gcd found
* */
public class GCDOfStrings {
    public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1))
            return "";

        int a = str1.length();
        int b = str2.length();
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return str1.substring(0, a);
    }

    /*FAILED FOR FEW CASES*/
    public String gcdOfStringsBruteForce(String str1, String str2) {
        int a = str1.length();
        int b = str2.length();
        int minLength = Math.min(a, b);
        if (str1.length() < str2.length()) {
            String temp = str1;
            str1 = str2;
            str2 = temp;
        }
        int j=0;
        for (int i = minLength - 1; i >= 0; i--) {
           if(find(str1,str2.substring(0,i+1))){
               return str2.substring(0,i+1);
           }
        }
        return "";
    }

    boolean find(String s1, String s2){
        for(int j=0;j<s1.length();j++){
            if(s1.indexOf(s2,j)>=0){
                j=j+s2.length()-1;
            }else
                return false;
        }
        return true;
    }

    /*USING RECURSION*/
    public String gcdOfStringsRecursion(String str1, String str2) {
        if(str1.length()<str2.length())
            return gcdOfStringsRecursion(str2,str1);
        if(str1.equals(str2))
            return str1;
        if(str1.startsWith(str2))
            return gcdOfStringsRecursion(str1.substring(str2.length()),str2);
        return "";
    }
    public static void main(String[] args) {
//        System.out.println(new GCDOfStrings().gcdOfStringsRecursion("ABCABC", "ABC"));
//        System.out.println(new GCDOfStrings().gcdOfStringsRecursion("ABABAB", "ABAB"));
//        System.out.println(new GCDOfStrings().gcdOfStringsRecursion("LEET", "CODE"));

//        System.out.println(new GCDOfStrings().gcdOfStrings("ABCABC", "ABC"));
                System.out.println(new GCDOfStrings().gcdOfStrings("ABABAB", "ABAB"));


    }
}
