package binary_search;

/*Set start=0 and end=n-1.

While start < end, find mid = (start + end) / 2.

If nums[mid] > nums[end], search right side; else, search left side including mid.

Return nums[start] (smallest element).

Time Complexity → O(log n)
*/
public class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        int start=0;int end=nums.length-1;
        while(start<end){
            int mid = start+((end-start)/2);
            if(nums[end]<nums[mid])
                start=mid+1;
            else
                end=mid;
        }
        return nums[start];
    }

    public static void main(String[] args) {
        System.out.println(new FindMinimumInRotatedSortedArray().findMin(new int[]{3,4,5,1,2}));
        System.out.println(new FindMinimumInRotatedSortedArray().findMin(new int[]{4,5,6,7,0,1,2}));
        System.out.println(new FindMinimumInRotatedSortedArray().findMin(new int[]{11,13,15,17}));
    }
}
