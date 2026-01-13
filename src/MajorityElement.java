import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/*
* Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.



Example 1:

Input: nums = [3,2,3]
Output: 3
Example 2:

Input: nums = [2,2,1,1,1,2,2]
Output: 2


Constraints:

n == nums.length
1 <= n <= 5 * 104
-109 <= nums[i] <= 109

* */
public class MajorityElement {
    public static void main(String[] args) {
//        System.out.println(majorityElement(new int[]{3, 2, 3}));
//        System.out.println(majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));
//
//        System.out.println(majorityElement2(new int[]{3, 2, 3}));
//        System.out.println(majorityElement2(new int[]{2, 2, 1, 1, 1, 2, 2}));
//
//        System.out.println(majorityElement3(new int[]{3, 3, 4}));
//        System.out.println(majorityElement3(new int[]{2, 2, 1, 1, 1, 2, 2}));

        System.out.println(majorityElement2(new int[]{1,1,1,1,1,2,3,4,5}));
        System.out.println(majorityElement2(new int[]{2, 2, 1, 1, 1, 2, 2}));
    }

    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> element = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (element.containsKey(nums[i])) {
                element.put(nums[i], element.get(nums[i]) + 1);
            } else {
                element.put(nums[i], 1);
            }
        }
        for (Integer i : element.keySet()) {
            if (element.get(i) > nums.length/2){
                return i;
            }
        }
        return 0;
    }

    /*using Arrays.sort*/
    public static int majorityElement2(int[] nums) {
        Map<Integer, Integer> element = new HashMap<>();
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    /*Using Moore Voting Algorithm
    * The intuition behind the Moore's Voting Algorithm is based on the fact that if there is a majority element in an array, it will always remain in the lead, even after encountering other elements.
    *
    * */
    public static int majorityElement3(int[] nums) {
        int count =0;
        int element=-1;
        for(int i=0;i<nums.length;i++){
            if(count == 0){
                element=nums[i];
                count++;
            }else if(element==nums[i]){
                count++;
            }else
                count--;
        }
        return element;
    }
}
