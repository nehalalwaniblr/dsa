import java.util.Arrays;

public class ProductExceptSelf {
    /*
    Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

    The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

    You must write an algorithm that runs in O(n) time and without using the division operation.



    Example 1:

    Input: nums = [1,2,3,4]
    Output: [24,12,8,6]
    Example 2:

    Input: nums = [-1,1,0,-3,3]
    Output: [0,0,9,0,0]


    Constraints:

    2 <= nums.length <= 105
    -30 <= nums[i] <= 30
    The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.


    Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.
    * */
    public static int[] productExceptSelf(int[] nums) {
//        int[] prefix = new int[nums.length];
//        int[] suffix = new int[nums.length];
//        Arrays.fill(prefix, 1);
//        Arrays.fill(suffix, 1);
//        int[] result = new int[nums.length];
//        for (int i = 1; i < nums.length; i++) {
//            prefix[i] = prefix[i - 1] * nums[i - 1];
//        }
//        for (int i = nums.length - 2; i >= 0; i--) {
//            suffix[i] = suffix[i + 1] * nums[i + 1];
//        }
//
//        for (int i = 0; i < nums.length; i++) {
//            result[i] = prefix[i] * suffix[i];
//        }
//        return result;

        int n = nums.length;
        int[] answer = new int[n];
        for(int i = 0; i < n; i++){
            answer[i] = 1;
        }
        int leftProduct = 1;
        for(int i = 0; i<n; i++){
            answer[i] = leftProduct;
            leftProduct *= nums[i];
        }
        int rightProduct = 1;
        for(int i = n -1; i>=0; i--){
            answer[i] *= rightProduct;
            rightProduct *= nums[i];
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(productExceptSelf(new int[]{1,2,3,4})));
    }
}
