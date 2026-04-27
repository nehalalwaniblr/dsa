package leet_again.stack;

import java.util.Stack;

/*https://www.youtube.com/watch?v=3AEKyHx3tzU*/
public class BasicCalculator {
    Stack<Integer> stack = new Stack<>();
    int result=0;
    int number =0;
    int sign =1;
    public int calculate(String s) {
        for (Character c: s.toCharArray()){

            if(Character.isDigit(c)){
//                create the number
                number = number*10+c-'0';

            }else if(c=='+'){
                result +=number*sign;
                number=0;
                sign=1;
            }else if(c=='-'){
                result +=number*sign;
                number=0;
                sign=-1;
            }else if(c=='('){
                stack.push(result);
                stack.push(sign);
                result = 0;
                number = 0;
                sign = 1;
            }else if(c==')'){
                result += (number*sign);
                number=0;
                int sign = stack.pop();
                int lastResult =  stack.pop();

                result*= sign;
                result+=lastResult;
            }
        }
        result+=number*sign;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new BasicCalculator().calculate("1 + 1"));
        System.out.println(new BasicCalculator().calculate("2-1+2"));
        System.out.println(new BasicCalculator().calculate("(1+(4+5+2)-3)+(6+8)"));

    }
}
