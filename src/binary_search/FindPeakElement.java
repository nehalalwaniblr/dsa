package binary_search;
/*A peak element is an element that is strictly greater than its neighbors.

Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.

You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.

You must write an algorithm that runs in O(log n) time.



Example 1:

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
Example 2:

Input: nums = [1,2,1,3,5,6,4]
Output: 5
Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.


Constraints:

1 <= nums.length <= 1000
-231 <= nums[i] <= 231 - 1
nums[i] != nums[i + 1] for all valid i. *************this is important; the array has no plateaus

*/

public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        if(nums==null)
            return -1;
        if(nums.length==2)
            return nums[0]>nums[1]?0:1;
        if(nums.length==1)
            return 0;
        int start=0;
        int end=nums.length-1;
        while(start<=end){
            int mid = start + ((end - start) / 2);
            if(mid-1>=0 && mid+1< nums.length && nums[mid]>nums[mid-1] && nums[mid]>nums[mid+1])
                return mid;
            if(mid+1< nums.length && nums[mid+1]>nums[mid])
                start=mid+1;
            else
                end=mid-1;
        }
        return start;
    }

    public static void main(String[] args) {
//        System.out.println(new FindPeakElement().findPeakElement(new int[]{1,2,3,1}));
//        System.out.println(new FindPeakElement().findPeakElement(new int[]{1,2,1,3,5,6,4}));
//        System.out.println(new FindPeakElement().findPeakElement(new int[]{6,5,4,3,2,3,2}));
//        System.out.println(new FindPeakElement().findPeakElement(new int[]{3,4,3,2,1}));
        System.out.println(new FindPeakElement().findPeakElement(new int[]{1,1,1,1,1,1,2,3,1}));

    }
}
