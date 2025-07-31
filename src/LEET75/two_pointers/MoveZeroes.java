package LEET75.two_pointers;

import java.util.Arrays;

/*
* Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Note that you must do this in-place without making a copy of the array.



Example 1:

Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]
Example 2:

Input: nums = [0]
Output: [0]


Constraints:

1 <= nums.length <= 104
-231 <= nums[i] <= 231 - 1


Follow up: Could you minimize the total number of operations done?
* */
public class MoveZeroes {
    int i = 0;        // for all elements
    int j = 0;        //for non-zero elements

    public void moveZeroes(int[] nums) {
        if(nums.length==1)
            return;
        //Input: nums = [0,1,0,3,12]
        while (i < nums.length && j < nums.length) {
            while (j < nums.length && nums[j] == 0) {
                j++;
            }
            if (nums[i] == 0 && j< nums.length) {
                nums[i] = nums[j];
                nums[j] = 0;
            }
            i++;
            j++;
        }
        System.out.println(Arrays.toString(nums));
    }

    public static void main(String[] args) {
//        new MoveZeroes().moveZeroes(new int[]{0, 1, 0, 3, 12});
        new MoveZeroes().moveZeroes(new int[]{0, 0});

    }
}
