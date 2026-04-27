package leet_again.dp.twoDp;

public class EditDistance {
    int m, n;

    public int minDistance(String word1, String word2) {
        m = word1.length();
        n = word2.length();
        return solve(word1, word2, 0, 0);
    }

    int solve(String word1, String word2, int i, int j) {
        if (word1.equals(word2))
            return 0;
        //if first string has completed all characters but 2nd string still has some char left then in order to make s1 to s2
        // then you need to delete n-j characters from s2
        if (i >= m) {
            return n - j + 1;
        }
        //if second string has completed all characters but 1st string still has some char left then in order to make s1 to s2
        // then you need to insert m-i characters to s1
        if (j >= n)
            return m - i + 1;
        if (word1.charAt(i) == word2.charAt(j)) {
            return solve(word1, word2, i + 1, j + 1);
        } else {
            //insert
            int insert = solve(word1, word2, i, j + 1);
            int delete = solve(word1, word2, i + 1, j + 1);
            int replace = solve(word1, word2, i + 1, j + 1);
            return Math.min(insert, Math.min(delete, replace));
        }
    }
}
