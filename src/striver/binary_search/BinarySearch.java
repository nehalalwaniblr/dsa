package striver.binary_search;

public class BinarySearch {
    int binarySearch(int[] nums, int target){
        int left=0;int right=nums.length-1;


        while(left<=right){
            int mid =  left +(right-left)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]<target){
                left=mid+1;
            }else if(nums[mid]>target){
                right=mid-1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        int[] nums = new int[]{1,2,3,4,5,6,7,8,9,10};
        int target = 7;
        System.out.println(bs.binarySearch(nums,target)); //6

        target = 1;
        System.out.println(bs.binarySearch(nums,target)); //0

        target = 10;
        System.out.println(bs.binarySearch(nums,target)); //9

        target = 11;
        System.out.println(bs.binarySearch(nums,target)); //-1

        target = -1;
        System.out.println(bs.binarySearch(nums,target)); //-1
    }
}
