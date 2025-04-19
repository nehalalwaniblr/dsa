package LEET75.array_and_strings;

/*
*Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.



Example 1:

Input: nums = [1,2,3,4,5]
Output: true
Explanation: Any triplet where i < j < k is valid.
Example 2:

Input: nums = [5,4,3,2,1]
Output: false
Explanation: No triplet exists.
Example 3:

Input: nums = [2,1,5,0,4,6]
Output: true
Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.


Constraints:

1 <= nums.length <= 5 * 105
-231 <= nums[i] <= 231 - 1


Follow up: Could you implement a solution that runs in O(n) time complexity and O(1) space complexity?

* */
public class IncreasingTripletSubsequence {
    /*
    * There are multiple approaches to it:
    * 1. Brute force O(n^3)
    * 2. DP: see LIS ; patience sort
    * 3. Using 3 variables; consider n1 and n2 to be set ot INTEGER.MAX_VALUE; consider n3 to be current index val;
    * check if n3<n1; yes make n1= n3; else if n3<n2; yes make it n2=n3; this way we have ensured n1<n2;
    * now whatever no. comes next if it doesn't satisfies first two criterias that means n3 is the largest amongst the traversal hence return true
    * */
    public boolean increasingTriplet(int[] nums) {
        int nums1 = Integer.MAX_VALUE;
        int nums2 = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int nums3 = nums[i];
            if (nums3 <= nums1) {
                nums1 = nums3;
            } else if (nums3 <= nums2) {
                nums2 = nums3;
            } else
                return true;
        }
        return false;
    }

    /**  This solution will consider only consecutive increasing  triplet subsequence
     public boolean increasingTriplet(int[] nums) {
     int startIndex=0;
     for(int i=0;i<nums.length-1;i++){
     if(nums[i+1]<=nums[i])
     startIndex++;
     if(i-startIndex==2)
     return true;
     }
     return false;
     }
     */

    public static void main(String[] args) {

    }
}
