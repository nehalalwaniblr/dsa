package leet_again.sliding_window;

/*
* Use sliding window;
* whenever you see a window where the sum is equal or greater than target then you know that window has the sum
* Now you try to reduce the length(as mentioned in question find min length) by incrementing i,  i.e. reducing  the length of window and checking if that also sums to target
* if it fits the target at you keep trach of min lenght and try to further reduce the length.
* That's it
*
* */
public class MinimumSizeSubArraySum {
    public int minSubArrayLen(int target, int[] nums) {
        int i = 0;
        int j = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        while (i <= j && j < nums.length) {
            sum += nums[j];
            while (sum >= target) {
                minLength = Math.min(minLength, j - i + 1);
                sum -= nums[i];
                i++;
            }
            j++;
        }
        return minLength==Integer.MAX_VALUE?0:minLength;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumSizeSubArraySum().minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
        System.out.println(new MinimumSizeSubArraySum().minSubArrayLen(4, new int[]{1, 4, 4}));
        System.out.println(new MinimumSizeSubArraySum().minSubArrayLen(11, new int[]{1, 1, 1, 1, 1, 1, 1, 1}));


    }
}
