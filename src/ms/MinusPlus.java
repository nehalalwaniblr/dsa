package ms;

public class MinusPlus {
    public String solution(String S) {
        // Implement your solution here
        StringBuilder input = new StringBuilder(S);
        for (int index = input.indexOf("minus"); index >= 0; index = input.indexOf("minus", index + 1)) {
            input.replace(index, index + 5, "-");
        }

        for (int index = input.indexOf("plus"); index >= 0; index = input.indexOf("plus", index + 1)) {
            input.replace(index, index + 4, "+");
        }
        return String.valueOf(input);
    }

    public static void main(String[] args) {
        System.out.println(new MinusPlus().solution("minusplusminus"));
        System.out.println(new MinusPlus().solution("plusminusplusminus"));
        System.out.println(new MinusPlus().solution(""));


    }
}
