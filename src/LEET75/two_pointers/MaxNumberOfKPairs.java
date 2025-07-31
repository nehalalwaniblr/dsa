package LEET75.two_pointers;

import java.util.HashMap;
import java.util.Map;

/*You are given an integer array nums and an integer k.

In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.

Return the maximum number of operations you can perform on the array.



Example 1:

Input: nums = [1,2,3,4], k = 5
Output: 2
Explanation: Starting with nums = [1,2,3,4]:
- Remove numbers 1 and 4, then nums = [2,3]
- Remove numbers 2 and 3, then nums = []
There are no more pairs that sum up to 5, hence a total of 2 operations.
Example 2:

Input: nums = [3,1,3,4,3], k = 6
Output: 1
Explanation: Starting with nums = [3,1,3,4,3]:
- Remove the first two 3's, then nums = [1,4,3]
There are no more pairs that sum up to 6, hence a total of 1 operation.


Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 109
1 <= k <= 109*/
public class MaxNumberOfKPairs {
    /*
    * Create a map holding the ele and its count
    * everytime you encounter a variable check if its there in map; then check if k-current ele is in map; if so decrease their counts and increase the result count
    * done
    * */
    public int maxOperations(int[] nums, int k) {
        int result=0;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int count=map.getOrDefault(nums[i],0);
            map.put(nums[i],++count);
        }
        for(int i=0;i<nums.length;i++){
            if(map.get(nums[i])>0 && map.get(k-nums[i])!=null && map.get(k-nums[i])>0){
                map.put(nums[i],map.get(nums[i])-1);
                if(map.get(k-nums[i])>0){
                    map.put(k-nums[i],map.get(k-nums[i])-1);
                    result++;
                }else{
                    map.put(nums[i],map.get(nums[i])+1);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        System.out.println(new MaxNumberOfKPairs().maxOperations(new int[]{1,2,3,4},5));
//        System.out.println(new MaxNumberOfKPairs().maxOperations(new int[]{3,1,3,4,3},6));
//        System.out.println(new MaxNumberOfKPairs().maxOperations(new int[]{3,5,1,5},2));
        System.out.println(new MaxNumberOfKPairs().maxOperations(new int[]{2,5,4,4,1,3,4,4,1,4,4,1,2,1,2,2,3,2,4,2},3));

//,5,4,4,,3,4,4,,4,4,,,,,,3,2,4,2

    }
}
