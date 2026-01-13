package striver.arrays;

/*
* https://leetcode.com/problems/next-permutation/description/
*
* - longer prefix match
*
* - 1. find break point from right where nums[i]<nums[i+1]
* - 2. find just larger element than nums[i] from right side
* - swap both
* - 3. reverse from i+1 to end
* */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int breakPoint = -1;
        for(int i=n-2;i>=0;i--){
            if(nums[i]<nums[i+1]){
                breakPoint = i;
                break;
            }
        }

        if(breakPoint == -1){
            reverse(nums, -1, n);
            return;
        }
        for(int i=n-1;i>=0;i--){
            if(nums[i]>nums[breakPoint]){
                //swap
                int temp = nums[i];
                nums[i]= nums[breakPoint];
                nums[breakPoint] = temp;
                break;
            }
        }

        reverse(nums, breakPoint, n);
    }

    private static void reverse(int[] nums, int breakPoint, int n) {
        //reverse starting breakpoint+1
        int start = breakPoint +1;
        int end = n -1;
        while(start<end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        NextPermutation np = new NextPermutation();
        int[] nums = new int[]{1,3,2};
        np.nextPermutation(nums);
        for(int num: nums){
            System.out.print(num+" ");
        }
    }
}

