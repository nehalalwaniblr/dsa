import java.util.Stack;

public class ReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                Integer operand1 = stack.pop();
                Integer operand2 = stack.pop();
                switch (token) {
                    case "+": {
                        stack.push(operand2 + operand1);
                        break;
                    }
                    case "-": {
                        stack.push(operand2 - operand1);
                        break;
                    }
                    case "*": {
                        stack.push(operand2 * operand1);
                        break;
                    }
                    case "/": {
                        stack.push(operand2 / operand1);
                        break;
                    }
                }
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        System.out.println(new ReversePolishNotation().evalRPN(new String[]{"4","13","5","/","+"}));
    }
}
