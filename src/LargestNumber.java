import java.util.*;
/*
* You are given 9 digits that are numbered from 1 to 9 and each digit has a cost.

The cost is denoted by an array X where X, denotes the cost of ith digit. You have N units of money to spend. You are required to find the largest number possible that can be formed by using the N units of money. If it is not possible to form such a number, then print -1.

Input format

First line: 7 denoting the number of test cases

For each test case:

First line: N denoting the amount of money

Second line: Nine space-separated integers X1, X2, X3 denoting the cost of each digit X.

Output format

Print the largest number possible which can be formed by using the N units of money. If it is not possible to form such a number, then print -1. For each test case, print the answer in a new line.

Constraints] <T≤ 101 ≤ N ≤ 101 ≤ X ≤ 10

Sample input

3

5

942263221

2

5 11 2 2 5 8 9 18 19

5

999999999*/
public class LargestNumber {

    public static String findLargestNumber(int N, int[] costs) {
        // Create an array of pairs (cost, digit)
        List<int[]> costDigitPairs = new ArrayList<>();
        for (int i = 0; i < costs.length; i++) {
            costDigitPairs.add(new int[]{costs[i], i + 1});
        }

        // Sort the pairs by cost (and by digit for tie-breaking, though not necessary here)
        costDigitPairs.sort((a, b) -> a[0] - b[0]);

        // Find the minimum cost digit
        int minCost = costDigitPairs.get(0)[0];
        int minDigit = costDigitPairs.get(0)[1];

        // If the minimum cost is greater than the available money, return "-1"
        if (minCost > N) {
            return "-1";
        }

        // Determine the maximum length of the number we can form
        int maxLength = N / minCost;
        int remainingMoney = N % minCost;

        // Create the initial number with the smallest digits
        int[] number = new int[maxLength];
        Arrays.fill(number, minDigit);

        // Try to maximize the number by replacing digits
        for (int i = 0; i < maxLength; i++) {
            for (int j = costDigitPairs.size() - 1; j >= 0; j--) {
                int cost = costDigitPairs.get(j)[0];
                int digit = costDigitPairs.get(j)[1];
                if (cost - minCost <= remainingMoney) {
                    number[i] = digit;
                    remainingMoney -= (cost - minCost);
                    break;
                }
            }
        }

        // Convert the number array to a string
        StringBuilder result = new StringBuilder();
        for (int digit : number) {
            result.append(digit);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int T = scanner.nextInt(); // Number of test cases
        for (int t = 0; t < T; t++) {
          int N = scanner.nextInt(); // Amount of money
            int[] costs = new int[9]; // Costs of digits 1 to 9
            for (int i = 0; i < 9; i++) {
                costs[i] = scanner.nextInt();
            }

            // Find and print the largest number for each test case
            System.out.println(findLargestNumber(N, costs));
        }

        scanner.close();
    }
}
