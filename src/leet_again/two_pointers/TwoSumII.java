package leet_again.two_pointers;

import java.util.Arrays;

/*
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/?envType=study-plan-v2&envId=top-interview-150
 * first approach: 2 loops; break when you see target number is smaller than next number as it array is increasing order O(n^2)
 * second approach: using binary search O(nlogn)
 * Third appraoch: using 2 pointers
 * ince the array is sorted:

The smallest number is at the beginning.
The largest number is at the end.
Instead of checking every possible pair (which would be slow),
we use a smarter strategy.

Imagine Two People

One person holds the smallest number.
One person holds the largest number.
Now check their total:

If the sum is too big →
Move the right pointer left (choose a smaller number).

If the sum is too small →
Move the left pointer right (choose a bigger number).

They keep adjusting until:

numbers[left] + numbers[right] == target
 * */
public class TwoSumII {
    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        int start = 0;
        int end = numbers.length - 1;
        while (start <= end) {
            if (numbers[start] + numbers[end] == target) {
                result[0] = start + 1;
                result[1] = end + 1;
                return result;
            } else if (numbers[start] + numbers[end] > target) {
                end--;
            } else {
                start++;
            }
        }
        return result;

    }

    public int[] twoSum3(int[] numbers, int target) {
        int[] result = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            int targetNumber = target - numbers[i];
            int index = binarySearch2(numbers, i + 1, targetNumber);
            if (index > -1) {
                result[0] = i + 1;
                result[1] = index + 1;
                return result;
            }
        }
        return result;

    }

    private int binarySearch2(int[] numbers, int i, int targetNumber) {
        int start = i;
        int end = numbers.length - 1;
        while (start <= end) { //this has to be less than equal to
            int mid = start + (end - start) / 2;
            if (numbers[mid] == targetNumber) {
                return mid;
            }
            if (numbers[mid] > targetNumber) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return -1;
    }

    public int[] twoSum2(int[] numbers, int target) {
        int[] result = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                int targetNumber = target - numbers[i];
                if (numbers[j] == targetNumber) {
                    result[0] = i + 1;
                    result[1] = j + 1;
                    return result;
                }
                if (numbers[j] > targetNumber)
                    break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new TwoSumII().twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(new TwoSumII().twoSum(new int[]{2, 3, 4}, 6)));
        System.out.println(Arrays.toString(new TwoSumII().twoSum(new int[]{-1, 0}, -1)));

    }
}
