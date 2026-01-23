package striver.binary_search;

import java.util.Arrays;

/*https://takeuforward.org/data-structure/aggressive-cows-detailed-solution*/
public class AggressiveCows {
    public static void main(String[] args) {
        AggressiveCows aggressiveCows = new AggressiveCows();
        int[] stalls = new int[]{1, 2, 4, 8, 9};
        int cows = 3;
        System.out.println(aggressiveCows.aggressiveCows(stalls, cows)); //3

        stalls = new int[]{10, 1, 2, 7, 5};
        cows = 3;
        System.out.println(aggressiveCows.aggressiveCows(stalls, cows)); //4

        stalls = new int[]{0,3,4,7,10,9};
        cows = 4;
        System.out.println(aggressiveCows.aggressiveCows(stalls, cows));//3

        stalls = new int[]{4,2,1,3,6};
        cows = 2;
        System.out.println(aggressiveCows.aggressiveCows(stalls, cows));//5


        stalls = new int[]{1, 2, 4, 8, 9};
        cows = 3;
        System.out.println(aggressiveCows.aggressiveCows2(stalls, cows)); //3

        stalls = new int[]{10, 1, 2, 7, 5};
        cows = 3;
        System.out.println(aggressiveCows.aggressiveCows2(stalls, cows)); //4

        stalls = new int[]{0,3,4,7,10,9};
        cows = 4;
        System.out.println(aggressiveCows.aggressiveCows2(stalls, cows));//3

        stalls = new int[]{4,2,1,3,6};
        cows = 2;
        System.out.println(aggressiveCows.aggressiveCows2(stalls, cows));//5
    }

    //linear search
    public int aggressiveCows(int[] stalls, int cows) {
        Arrays.sort(stalls);
        int maxDistance = stalls[stalls.length - 1] - stalls[0];
        int result = 0;
        for (int i = 1; i <= maxDistance; i++) {
            if (canPlaceCows(stalls, cows, i)) {
                result = i;
            }
        }
        return result;
    }

//    Binary search - instead of iterating from 1 to maxDistance, we can do binary search
    public int aggressiveCows2(int[] stalls, int cows) {
        Arrays.sort(stalls);
        int maxDistance = stalls[stalls.length - 1] - stalls[0];
        int result = 0;
        int start =1;int end=maxDistance;
        while(start<=end){
            int  mid = start + (end-start)/2;
            if(canPlaceCows(stalls, cows, mid)){
                result = mid;
                start= mid+1;
            }else{
                end = mid-1;
            }
        }
        return result;
    }


    private boolean canPlaceCows(int[] stalls, int cows, int minDist) {
        int countOfCows = 1;
        int lastPosition = stalls[0];
        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - lastPosition >= minDist) {
                countOfCows++;
                lastPosition = stalls[i];
            }
            if (countOfCows >= cows)
                return true;
        }
        return false;
    }
}
