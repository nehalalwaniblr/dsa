package LEET75.array_and_strings;

/*You are given two strings word1 and word2. Merge the strings by adding letters in alternating order, starting with word1. If a string is longer than the other, append the additional letters onto the end of the merged string.

Return the merged string.



Example 1:

Input: word1 = "abc", word2 = "pqr"
Output: "apbqcr"
Explanation: The merged string will be merged as so:
word1:  a   b   c
word2:    p   q   r
merged: a p b q c r
Example 2:

Input: word1 = "ab", word2 = "pqrs"
Output: "apbqrs"
Explanation: Notice that as word2 is longer, "rs" is appended to the end.
word1:  a   b
word2:    p   q   r   s
merged: a p b q   r   s
Example 3:

Input: word1 = "abcd", word2 = "pq"
Output: "apbqcd"
Explanation: Notice that as word1 is longer, "cd" is appended to the end.
word1:  a   b   c   d
word2:    p   q
merged: a p b q c   d*/
public class MergeStringsAlternatively {
    int i=0;int j=0;
    int len=0;
    public String mergeAlternately(String word1, String word2) {
        StringBuilder result = new StringBuilder();
        if(word1.length()>=word2.length()){
            len= word1.length();
        }else
            len= word2.length();
        for(int k=0;k<len;k++){
            if(i>=word1.length() || j>=word2.length())
                break;
            result.append(word1.charAt(i));
            result.append(word2.charAt(j));
            i++;
            j++;
        }
        if(word1.length()-1!=i){
            for(int k=i;k<word1.length();k++)
                result.append(word1.charAt(k));
        }

        if(word2.length()-1!=j){
            for(int k=j;k<word2.length();k++)
                result.append(word2.charAt(k));
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(new MergeStringsAlternatively().mergeAlternately("abc","pqr"));
        System.out.println(new MergeStringsAlternatively().mergeAlternately("ab","pqrs"));
        System.out.println(new MergeStringsAlternatively().mergeAlternately("abcd","pq"));

    }
}
