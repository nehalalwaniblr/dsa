package kadane;

public class MaximumSumCircularSubarray {
    public int maxSubarraySumCircular(int[] nums) {
        int maxSum = kadanesMaxSum(nums);
        int minSum = kadanesMinSum(nums);
        int totalSum = 0;
        for(int i=0;i<nums.length;i++){
            totalSum+=nums[i];
        }
        if(maxSum>0)
            return Math.max(maxSum,totalSum-minSum);
        return maxSum;
    }

    int kadanesMaxSum(int[] nums){
        int currentSubArraySum = nums[0];
        int maxSubArraySum = nums[0];
        for(int i=1;i<nums.length;i++){
            currentSubArraySum = Math.max(currentSubArraySum+nums[i],nums[i]);
            maxSubArraySum = Math.max(currentSubArraySum,maxSubArraySum);
        }
        return maxSubArraySum;
    }

    int kadanesMinSum(int[] nums){
        int currentSubArraySum = nums[0];
        int minSubArraySum = nums[0];
        for(int i=1;i<nums.length;i++){
            currentSubArraySum = Math.min(currentSubArraySum+nums[i],nums[i]);
            minSubArraySum = Math.min(currentSubArraySum,minSubArraySum);
        }
        return minSubArraySum;
    }

    public static void main(String[] args) {
        System.out.println(new MaximumSumCircularSubarray().maxSubarraySumCircular(new int[]{1,-2,3,-2}));
        System.out.println(new MaximumSumCircularSubarray().maxSubarraySumCircular(new int[]{5,-3,5}));
        System.out.println(new MaximumSumCircularSubarray().maxSubarraySumCircular(new int[]{-3,-2,-3}));
    }
}
