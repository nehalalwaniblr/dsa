package kadane;

public class KadaneMaximumSubArray {
    /*
     * Kadane algorith says:
     * At any index i shall I start a new array or add the current element to existing subarray
     * i.e. initialize currentSubArraySum=a[0]; now check if you should add a[1] to currentSubArraySum(a[1]+currentSubArraySum) or start a new subaaray i.e.currentSubArraySum=a[1]
     * */
    public int maxSubArray(int[] nums) {
        /*
        * maxSubArray normal using kadanes algo
        * minSubArray normal using kadanes algo
        * total sum
        * totalsum minus minsubarray give max circular sub array sum
        * */
        if (nums == null)
            return 0;
        int currentSubArraySum = nums[0];
        int maxSubArraySum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currentSubArraySum = Math.max(nums[i], nums[i]+currentSubArraySum);
            maxSubArraySum = Math.max(currentSubArraySum,maxSubArraySum);
        }
        return maxSubArraySum;
    }

    public static void main(String[] args) {
        System.out.println(new KadaneMaximumSubArray().maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
    }
}
