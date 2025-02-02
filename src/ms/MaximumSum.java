package ms;

import java.util.HashMap;
import java.util.Map;

public class MaximumSum {
    public int solution(int[] A) {
        Map<String, Integer> maxPairSum = new HashMap<>();
        int maxSum = -1;

        for (int num : A) {
            if (num > 0) {
                String pair = firstAndLastDigitKey(num);
                if (maxPairSum.containsKey(pair)) {
                    maxSum = Math.max(maxSum, maxPairSum.get(pair) + num);
                }
                maxPairSum.put(pair, Math.max(maxPairSum.getOrDefault(pair, 0), num));
            }
        }

        return maxSum;
    }
    private String firstAndLastDigitKey(int num) {
        String s = Integer.toString(num);
        return s.charAt(0) + ":" + s.charAt(s.length() - 1);

    }
    public static void main(String[] args) {
        System.out.println(new MaximumSum().solution(new int[]{130,191,200,10}));
        System.out.println(new MaximumSum().solution(new int[]{405,45,300,300}));
        System.out.println(new MaximumSum().solution(new int[]{50,222,49,52,25}));

    }
}
