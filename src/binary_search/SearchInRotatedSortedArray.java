package binary_search;
/*Setup

Two pointers: start (beginning of array) and end (end of array).

Standard binary search loop: while (start <= end).

Midpoint Check

mid is calculated.

If nums[mid] == target, we found the answer — return mid.

Determine Which Side Is Sorted

Left side sorted: If nums[start] <= nums[mid]

Check if target lies between nums[start] and nums[mid] (inclusive).

If yes → move end left to mid - 1.

If no → search in the right half (start = mid + 1).

Right side sorted: Else case.

Check if target lies between nums[mid] and nums[end] (inclusive).

If yes → move start right to mid + 1.

If no → search in the left half (end = mid - 1).

If Not Found

If the loop ends without finding target, return -1.

NOTE:
 in a rotated sorted array with no duplicates,
at least one half of the array will always be sorted in every step of binary search.


*/
public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + ((end - start) / 2);
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[start] <= nums[mid]) {
                if(target>=nums[start] && target<=nums[mid]){
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target >= nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else
                    end = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new SearchInRotatedSortedArray().search(new int[]{4,5,6,7,0,1,2},0));
        System.out.println(new SearchInRotatedSortedArray().search(new int[]{4,5,6,7,0,1,2},3));
        System.out.println(new SearchInRotatedSortedArray().search(new int[]{15,18,2,3,6,12},3));


    }
}
