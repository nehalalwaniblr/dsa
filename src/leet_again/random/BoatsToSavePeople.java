package leet_again.random;

import java.util.Arrays;
//https://leetcode.com/problems/boats-to-save-people/description/
public class BoatsToSavePeople {
    public int numRescueBoats(int[] people, int limit) {
        int result =0;
        int left =0;
        int right = people.length-1;
        Arrays.sort(people);
        while(left<right){
            if(people[left]+people[right]<=limit){
                left++;
            }
            right--;
            result++;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new BoatsToSavePeople().numRescueBoats(new int[]{1,2},3));
        System.out.println(new BoatsToSavePeople().numRescueBoats(new int[]{3,2,2,1},3));
        System.out.println(new BoatsToSavePeople().numRescueBoats(new int[]{3,5,3,4},5));
        System.out.println(new BoatsToSavePeople().numRescueBoats(new int[]{5,1,4,2},6));

    }

}
