package leet_again.array_strings;

import java.util.Arrays;

/*
 * Solution is to start from end since at the end we have empty array which can be utilized
 * 3 pts i,j, and current(where it should be inserted)
 * also, take care of if one array got finished before other
 * */
public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int current = m + n - 1;
        while (current >= 0) {
            //if first array finishes
            if (i < 0 && j >= 0) {
                while (j >= 0) {
                    nums1[current] = nums2[j];
                    current--;
                    j--;
                }
                break;
            }
            //if second array finishes
            if (j < 0 && i >= 0) {
                while (i >= 0) {
                    nums1[current] = nums1[i];
                    current--;
                    i--;
                }
                break;
            }


            //adjust the numbers
            if (nums2[j] >= nums1[i]) {
                nums1[current] = nums2[j];
                j--;
            } else {
                nums1[current] = nums1[i];
                i--;
            }
            current--;
        }
    }

    public static void main(String[] args) {
        int[] result = new int[]{1, 2, 3, 0, 0, 0};
        new MergeSortedArray().merge(result, 3, new int[]{2, 5, 6}, 3);
        System.out.println(Arrays.toString(result));
    }
}
