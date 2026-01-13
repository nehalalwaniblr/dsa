package striver.arrays;

public class MissingAndRepeatingNumbers {
    // Function to find repeating and missing numbers
    public int[] findMissingRepeatingNumbers(int[] nums) {

        // Size of the array
        long n = nums.length;

        // Sum of first n natural numbers
        long SN = (n * (n + 1)) / 2;

        // Sum of squares of first n natural numbers
        long S2N = (n * (n + 1) * (2 * n + 1)) / 6;

        // Calculate actual sum (S) and sum of squares (S2) of array elements
        long S = 0, S2 = 0;
        for (int i = 0; i < n; i++) {
            S += nums[i];
            S2 += (long) nums[i] * (long) nums[i];
        }

        // Compute the difference values
        long val1 = S - SN; // X - Y

        // S2 - S2n = X^2 - Y^2
        long val2 = S2 - S2N;

        // Calculate X + Y
        val2 = val2 / val1;

        // Calculate X and Y; x-y=-1 and x+y=3; 2x=2;x=2/2
        long x = (val1 + val2) / 2; // repeating
        long y = x - val1;          // missing

        return new int[]{(int) x, (int) y};
    }

    public static void main(String[] args) {
        int[] nums = {3, 5, 4, 1, 1};

        // Create an instance of Solution class
        MissingAndRepeatingNumbers sol = new MissingAndRepeatingNumbers();

        int[] result = sol.findMissingRepeatingNumbers(nums);

        // Print the repeating and missing numbers found
        System.out.printf("The repeating and missing numbers are: {%d, %d}\n", result[0], result[1]);

    }

}


