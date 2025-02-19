package binary_search;

/*Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You must write an algorithm with O(log n) runtime complexity.



Example 1:

Input: nums = [1,3,5,6], target = 5
Output: 2
Example 2:

Input: nums = [1,3,5,6], target = 2
Output: 1
Example 3:

Input: nums = [1,3,5,6], target = 7
Output: 4
*/
public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        if (nums.length == 0)
            return -1;
        int start = 0;
        int end = nums.length-1;
        while(start<=end){
            int mid = start + ((end - start) / 2);
            if(nums[mid]==target)
                return mid;
            if(target<nums[mid])
                end=mid-1;
            else
                start=mid+1;
        }
        return start;
    }

    public static void main(String[] args) {
//        System.out.println(new SearchInsertPosition().searchInsert(new int[]{1,3,5,6},5));
//        System.out.println(new SearchInsertPosition().searchInsert(new int[]{1,3,5,6},2));
//        System.out.println(new SearchInsertPosition().searchInsert(new int[]{1,3,5,6},7));
        System.out.println(new SearchInsertPosition().searchInsert(new int[]{1,3},2));

    }
}
