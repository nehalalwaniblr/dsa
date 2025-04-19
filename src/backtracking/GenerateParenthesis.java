package backtracking;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*

*
 Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.



Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]


Constraints:

1 <= n <= 8
 */
public class GenerateParenthesis {
    List<String> result = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        solve(n, "");
        return result;
    }

    private void solve(int n, String s) {
        if (s.length() == n * 2) {
            if ((isValid(s)))
                result.add(s);
            return;
        }
        s = s + "(";
        solve(n, s);
        s = s.substring(0, s.length() - 1);
        s = s + ")";
        solve(n, s);
    }

    public static void main(String[] args) {
        System.out.println(new GenerateParenthesis().generateParenthesis(8));
    }

    boolean isValid(String input) {
        Stack<Character> stack = new Stack<>();
        if (input.isEmpty())
            return false;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                stack.push('(');
            } else if (input.charAt(i) == ')') {
                if (stack.isEmpty())
                    return false;
                stack.pop();
            } else return false;

        }
        return stack.isEmpty();
    }
}
