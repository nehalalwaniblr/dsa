package binary_search;

/*Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.



Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
Example 3:

Input: nums = [], target = 0
Output: [-1,-1]


*/
public class FindFirstAndLastInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + ((end - start) / 2);
            if (nums[mid] == target) {
                int newPtr = mid;
                while (newPtr - 1 >= 0 && nums[newPtr - 1] == target) {
                    newPtr--;
                }
                result[0] = newPtr;
                newPtr = mid;
                while (newPtr + 1 < nums.length && nums[newPtr + 1] == target) {
                    newPtr++;
                }
                result[1] = newPtr;
                return result;
            } else if (target < nums[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return result;
    }
}
