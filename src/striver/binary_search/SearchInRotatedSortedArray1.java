package striver.binary_search;

///*https://leetcode.com/problems/search-in-rotated-sorted-array/description/*/ no duplicates
public class SearchInRotatedSortedArray1 {
    public int search(int[] nums, int target) {
        int left=0;int right=nums.length-1;
        while(left<=right){
            int mid =  left +(right-left)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[left]<=nums[mid]){ //left part is sorted?
                if(nums[left]<=target && target<nums[mid]){
                    right=mid-1;
                }else{
                    left=mid+1;
                }
            }else if(nums[right]>=nums[mid]){ //right part is sorted?
                if(target>=nums[mid] && target<=nums[right]){
                    left=mid+1;
                }else{
                    right=mid-1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray1 searchInRotatedSortedArray1 = new SearchInRotatedSortedArray1();
        int[] nums = new int[]{4,5,6,7,0,1,2};
        int target = 0;
        System.out.println(searchInRotatedSortedArray1.search(nums,target)); //4

        target = 3;
        System.out.println(searchInRotatedSortedArray1.search(nums,target)); //-1

        nums = new int[]{1};
        target = 0;
        System.out.println(searchInRotatedSortedArray1.search(nums,target)); //-1
    }
}
