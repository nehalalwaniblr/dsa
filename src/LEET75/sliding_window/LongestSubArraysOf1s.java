package LEET75.sliding_window;

/*Given a binary array nums, you should delete one element from it.

Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.



Example 1:

Input: nums = [1,1,0,1]
Output: 3
Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
Example 2:

Input: nums = [0,1,1,1,0,1,1,0,1]
Output: 5
Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
Example 3:

Input: nums = [1,1,1]
Output: 2
Explanation: You must delete one element.


Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.*/
public class LongestSubArraysOf1s {
    /*
     * Brute force approach
     * 1. Iterate through the array, whenever you find a zero consider that to be deleted
     * 2. then scan the entire array except for that index where the zero was found and maintain the result of max sub-array length
     * 3. when there are no zeroes in the array return length-1
     *
     *
     * Sliding wondow
     * 1. Keep moving right pointer till your countOfZero>1
     * 2. Whenever it happens start moving the window - increasing leftPtr till count is >1;
     *   3. When zero count becomes 1 continue in the window search
     * */

    public int longestSubarray(int[] nums) {
        int result = 0;
        int left = 0;
        int zeroCount = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeroCount++;
            }
            if (zeroCount > 1) {
                while (zeroCount > 1) {
                    if (nums[left] == 0)
                        zeroCount--;
                    left++;
                }
            }
            result = Math.max(result, (right - left + 1) - 1);//(right - left + 1)-1 coz we need to not count 0
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new LongestSubArraysOf1s().longestSubarray(new int[]{1, 1, 0, 1}));
        System.out.println(new LongestSubArraysOf1s().longestSubarray(new int[]{0, 1, 1, 1, 0, 1, 1, 0, 1}));
        System.out.println(new LongestSubArraysOf1s().longestSubarray(new int[]{1, 1, 1}));
    }
}
