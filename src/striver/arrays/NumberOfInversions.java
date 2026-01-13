package striver.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*Problem Statement: Given an array of N integers, count the inversion of the array (using merge-sort).

Inversion of an array: for all i & j < size of array, if i < j then you have to find pair (A[i],A[j]) such that A[j] < A[i].

Example 1:
Input Format: N = 5, array[] = {1,2,3,4,5}
Result: 0
Explanation: we have a sorted array and the sorted array has 0 inversions as for i < j you will never find a pair such that A[j] < A[i]. More clear example: 2 has index 1 and 5 has index 4 now 1 < 5 but 2 < 5 so this is not an inversion.

Example 2:
Input Format: N = 5, array[] = {5,4,3,2,1}
Result: 10
Explanation: we have a reverse sorted array and we will get the maximum inversions as for i < j we will always find a pair such that A[j] < A[i]. Example: 5 has index 0 and 3 has index 2 now (5,3) pair is inversion as 0 < 2 and 5 > 3 which will satisfy out conditions and for reverse sorted array we will get maximum inversions and that is (n)*(n-1) / 2.For above given array there is 4 + 3 + 2 + 1 = 10 inversions.

Example 3:
Input Format: N = 5, array[] = {5,3,2,1,4}
Result: 7
Explanation: There are 7 pairs (5,1), (5,3), (5,2), (5,4),(3,2), (3,1), (2,1) and we have left 2 pairs (2,4) and (1,4) as both are not satisfy our condition.

          https://takeuforward.org/data-structure/count-inversions-in-an-array
*/
class NumberOfInversions {
    int result =0;
//    brute force approach O(n^2)
    public long numberOfInversions(int[] nums) {
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]>nums[j]){
                    result++;
                }
            }
        }
        return result;
    }
//    Optimal approach using merge sort O(n log n)
    void mergeSort(int[] nums, int low, int high){
        if(low>=high) return;
        int mid = low + (high-low)/2;
        mergeSort(nums,low,mid);
        mergeSort(nums,mid+1,high);
        merge(nums,low,mid,high);
    }

    void merge(int[] nums, int low, int mid, int high){
        int left = low;
        int right = mid+1;
        List<Integer> temp = new ArrayList<>();
        while(left<=mid && right<=high){
            if(nums[left]<=nums[right]){
                temp.add(nums[left]);
                left++;
            }else {
                temp.add(nums[right]);
                result+= (mid - left + 1);
                right++;
            }
        }
        while(left<=mid){
            temp.add(nums[left]);
            left++;
        }
        while(right<=high){
            temp.add(nums[right]);
            right++;
        }
        for(int i=0;i<temp.size();i++){
            nums[low+i] = temp.get(i);
        }
    }

    public static void main(String[] args) {
        NumberOfInversions noi = new NumberOfInversions();
        int[] nums = new int[]{2,4,1,3,5};
        noi.mergeSort(nums,0, nums.length-1);
//        System.out.println(Arrays.toString(nums));
        System.out.println("Result = "+noi.result);

        noi.result=0;
        nums = new int[]{5,3,2,1,4};
        noi.mergeSort(nums,0, nums.length-1);
//        System.out.println(Arrays.toString(nums));
        System.out.println("Result = "+noi.result);


        noi.result=0;
        nums = new int[]{1,2,3,4,5};
        noi.mergeSort(nums,0, nums.length-1);
//        System.out.println(Arrays.toString(nums));
        System.out.println("Result = "+noi.result);

        noi.result=0;
        nums = new int[]{5,4,3,2,1};
        noi.mergeSort(nums,0, nums.length-1);
//        System.out.println(Arrays.toString(nums));
        System.out.println("Result = "+noi.result);

    }
}