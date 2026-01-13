package striver.binary_search;

import java.util.Arrays;

//https://leetcode.com/problems/koko-eating-bananas/description/
/*Example 1:

Input: piles = [3,6,7,11], h = 8
Output: 4
Example 2:

Input: piles = [30,11,23,4,20], h = 5
Output: 30
Example 3:

Input: piles = [30,11,23,4,20], h = 6
Output: 23


Constraints:

1 <= piles.length <= 104
piles.length <= h <= 109
1 <= piles[i] <= 109*/
public class KokoEatingBanana {
    long totalHours;
    int result;
    public int minEatingSpeed(int[] piles, int h) {
        // Find maximum pile size
        int maxVal = Arrays.stream(piles).max().getAsInt();
        int left = 1;
        int right = maxVal;
        while (left <= right) {
            int mid = left + (right - left)/2;
            totalHours=0;
            for (int pile : piles) {
                totalHours += (int)Math.ceil((double)pile / mid);
            }
             if(totalHours<=h){ //// If possible, try smaller speed
                result = mid;
                right=mid-1;
            }else { //// Otherwise, try larger speed
                left=mid+1;
            }
        }
        return result;

    }

    public static void main(String[] args) {
        KokoEatingBanana kokoEatingBanana = new KokoEatingBanana();
        int[] piles = new int[]{7, 15, 6, 3};
        int h = 8;
        System.out.println(kokoEatingBanana.minEatingSpeed(piles, h)); //4

        piles = new int[]{30, 11, 23, 4, 20};
        h = 5;
        System.out.println(kokoEatingBanana.minEatingSpeed(piles, h)); //30

        piles = new int[]{30, 11, 23, 4, 20};
        h = 6;
        System.out.println(kokoEatingBanana.minEatingSpeed(piles, h)); //23

        piles = new int[]{805306368,805306368,805306368};
        h = 1000000000;
        System.out.println(kokoEatingBanana.minEatingSpeed(piles, h)); //23

    }
}

