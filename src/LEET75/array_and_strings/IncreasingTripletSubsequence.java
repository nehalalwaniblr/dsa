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
    * 3. Using 3 variables; consider n1 and n2 to be set to INTEGER.MAX_VALUE; consider n3 to be current index val;
    * check if n3<n1; yes make n1= n3; else if n3<n2; yes make it n2=n3; this way we have ensured n1<n2;
    * now whatever no. comes next if it doesn't satisfies first two criterias that means n3 is the largest amongst the traversal hence return true
    * */
    /*The Core Thought Process
We want to detect whether there exists a triplet (a < b < c) in order.
Instead of storing all possible subsequences (which would be costly), we track just two numbers:

nums1: The smallest number seen so far (candidate for the 1st element of triplet).

nums2: The second smallest number seen so far that comes after nums1 (candidate for the 2nd element of triplet).

Now, as soon as we see a number greater than both nums1 and nums2, that means we found a valid triplet.

Why This Works (Intuition)
We greedily keep track of the best possible first and second candidates.

By ensuring nums1 < nums2 always, as soon as we see a third number bigger than nums2, we guarantee an increasing triplet subsequence exists.

Time Complexity = O(n)

Space Complexity = O(1)

NOTE: for value of more than 3(triplets) use dp
*/
    public boolean increasingTriplet(int[] nums) {
        int nums1 = Integer.MAX_VALUE;  // smallest number seen so far
        int nums2 = Integer.MAX_VALUE;  // second smallest number (after nums1)

        for (int i = 0; i < nums.length; i++) {
            int nums3 = nums[i];  // current number
            if (nums3 <= nums1) {
                // found a new smaller number → update first smallest
                nums1 = nums3;
            } else if (nums3 <= nums2) {
                // found something bigger than nums1 but smaller than nums2 → update second smallest
                nums2 = nums3;
            } else {
                // found nums3 > nums1 and nums3 > nums2 → triplet exists
                return true;
            }
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
        new IncreasingTripletSubsequence().increasingTriplet(new int[] {2,1,5,0,4,6});

    }
}
