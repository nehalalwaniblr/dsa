package LEET75.prefix_sum;

public class FindPivotIndex {
    /*Given an array of integers nums, calculate the pivot index of this array.

The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.

If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left. This also applies to the right edge of the array.

Return the leftmost pivot index. If no such index exists, return -1.



Example 1:

Input: nums = [1,7,3,6,5,6]
Output: 3
Explanation:
The pivot index is 3.
Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
Right sum = nums[4] + nums[5] = 5 + 6 = 11
Example 2:

Input: nums = [1,2,3]
Output: -1
Explanation:
There is no index that satisfies the conditions in the problem statement.
Example 3:

Input: nums = [2,1,-1]
Output: 0
Explanation:
The pivot index is 0.
Left sum = 0 (no elements to the left of index 0)
Right sum = nums[1] + nums[2] = 1 + -1 = 0


Constraints:

1 <= nums.length <= 104
-1000 <= nums[i] <= 1000


Note: This question is the same as 1991: https://leetcode.com/problems/find-the-middle-index-in-array/*/
    public int pivotIndex(int[] nums) {
        /*Create a  new array ; Find the left sum at each index
        * find the right sum at each index
        * compare both arrays return the index where left[i]==right[i]; else return -1
        * */
        int[] left= new int[nums.length];
        int[] right= new int[nums.length];
        left[0]=0;
        for(int i=1;i<nums.length;i++){
            left[i]= left[i-1]+nums[i-1];
        }
        right[nums.length-1]=0;
        for(int i=nums.length-1;i>0;i--){
            right[i-1]= right[i]+nums[i];
        }
        for(int i=0;i<nums.length;i++){
            if(left[i]==right[i])
                return i;
        }
        return -1;
    }

    /**Not using arrays
     - Calculate totalSum
     - Iterate all over and at each index check if you subtract leftSum and current ele from totalSum will it make equal to leftSum--think why thenreturn i
     else keep adding current ele to leftSum
     */
    public int pivotIndex2(int[] nums) {
        int totalSum=0;
        int leftSum=0;
        for(int i=0;i<nums.length;i++){
            totalSum+=nums[i];
        }
        for(int i=0;i<nums.length;i++){
            if(leftSum==(totalSum-leftSum-nums[i])){
                return i;
            }else{
                leftSum+=nums[i];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new FindPivotIndex().pivotIndex(new int[]{1,7,3,6,5,6}));
        System.out.println(new FindPivotIndex().pivotIndex(new int[]{1,2,3}));
        System.out.println(new FindPivotIndex().pivotIndex(new int[]{2,1,-1}));

    }
}
