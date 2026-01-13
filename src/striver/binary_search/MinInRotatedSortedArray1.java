package striver.binary_search;

///*https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description//*/
public class MinInRotatedSortedArray1 {
    public int findMin(int[] nums) {
        int left=0;int right=nums.length-1;
        int min = Integer.MAX_VALUE;
        while(left<=right){
            int mid =  left +(right-left)/2;
             if(nums[left]<=nums[mid]){ //left part is sorted?
               min = Math.min(min,nums[left]);
               left=mid+1;
            }else if(nums[right]>=nums[mid]){ //right part is sorted?
                 min = Math.min(min,nums[mid]);
                    right=mid-1;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        MinInRotatedSortedArray1 searchInRotatedSortedArray1 = new MinInRotatedSortedArray1();
        int[] nums = new int[]{3,4,5,1,2};
        int target = 0;
        System.out.println(searchInRotatedSortedArray1.findMin(nums));

        nums = new int[]{4,5,6,7,0,1,2};
        System.out.println(searchInRotatedSortedArray1.findMin(nums));

        nums = new int[]{11,13,15,17};
        System.out.println(searchInRotatedSortedArray1.findMin(nums));
    }
}
