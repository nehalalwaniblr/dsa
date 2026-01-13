package striver.arrays;

import java.util.HashMap;
import java.util.Map;

/*Count the number of subarrays with given xor K



5

Problem Statement: Given an array of integers A and an integer B. Find the total number of subarrays having bitwise XOR of all elements equal to k.

Examples
Input: A = [4, 2, 2, 6, 4] , k = 6
Output: 4
Explanation: The subarrays having XOR of their elements as 6 are  [4, 2], [4, 2, 2, 6, 4], [2, 2, 6], [6]
Input: A = [5, 6, 7, 8, 9], k = 5
Output: 2
Explanation: The subarrays having XOR of their elements as 5 are [5] and [5, 6, 7, 8, 9]
https://takeuforward.org/data-structure/count-the-number-of-subarrays-with-given-xor-k
*/
public class SubArrayWithXor {
    public static void main(String[] args) {
        SubArrayWithXor subArrayWithXor = new SubArrayWithXor();
        int[] arr = new int[]{4, 2, 2, 6, 4};
        int k = 6;
        System.out.println(subArrayWithXor.countSubarrays(arr, k));
        
        arr = new int[]{5, 6, 7, 8, 9};
        k = 5;
        System.out.println(subArrayWithXor.countSubarrays(arr, k));
    }

    public int subarraysWithXorK(int[] nums, int k) {
        int result =0;
        for(int i=0;i<nums.length;i++){
            int xor = 0;
            for(int j=i;j<nums.length;j++){
                xor = xor^nums[j];
                if(xor==k){
                    result++;

                }
            }
        }
        return result;
    }

    // Function to count subarrays with given XOR
    public int countSubarrays(int[] A, int k) {
        // Store frequency of prefix XORs
        Map<Integer, Integer> freq = new HashMap<>();
        // Initialize with prefix XOR 0
        freq.put(0, 1);

        // Current prefix XOR
        int prefixXor = 0;
        // Answer count
        int count = 0;

        // Traverse array
        for (int num : A) {
            // Update prefix XOR
            prefixXor ^= num;

            // Compute required XOR
            int target = prefixXor ^ k;

            // If target exists in map, add its frequency
            if (freq.containsKey(target)) {
                count += freq.get(target);
            }

            // Store current prefix XOR in map
            freq.put(prefixXor, freq.getOrDefault(prefixXor, 0) + 1);
        }
        return count;
    }
}
