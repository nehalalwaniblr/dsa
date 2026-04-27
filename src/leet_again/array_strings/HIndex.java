package leet_again.array_strings;

import java.util.Arrays;
/**
 Approach 1:
 1, Here intuition is sort array in ascending order and start from beginning. say i =0 to n-1
 2. check if there is atleast n-i papers i.e. say 5-0 =5 papers with citations 5; if the smallest number itself is max papers that are given then it is your result.
 //complexity O(n*logn)

 //Approach 2:
 1. Bucket sort: create an array maintaing frequency of each citation
 2. iterate in this array from last and check if this is greater than current index

 */
public class HIndex {
    public int hIndex(int[] citations) {
        int n = citations.length;
        Arrays.sort(citations);
        for(int i=0;i<n;i++){
            int h= n-i;
            if(citations[i]>=h)
                return h;
        }
        return 0;
    }

    //optimal
    public int hIndex2(int[] citations) {

        int n = citations.length;
        int[] count = new int[n + 1];

        for (int c : citations) {
            if (c >= n) count[n]++;
            else count[c]++;
        }

        int papers = 0;

        for (int h = n; h >= 0; h--) {
            papers += count[h];
            if (papers >= h) return h;
        }

        return 0;
    }

    public static void main(String[] args) {
        new HIndex().hIndex2(new int[]{3,0,6,1,5});
        new HIndex().hIndex2(new int[]{1,3,1});
    }
}
