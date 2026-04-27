package leet_again.dp;

import java.util.Arrays;

public class MinRoundsToCompleteTasks {
    public int minimumRounds(int[] tasks) {
        int n = tasks.length;
        //sort the array
        Arrays.sort(tasks);
        int i=0;
        int j=0;
        int result=0;
        //2,2,2,3,3,4,4,4,4,4
        while(i<n){
            //iterate to find same elements
            while( j<n && tasks[i]==tasks[j] ){
                j++;
            }
            //Number of same elements
            int count = j-i;
            if(count<2){
                return -1;
            }
            int div = Math.ceilDiv(count,3);
            if(div==0){
                div = Math.ceilDiv(count,2);
            }
            result+=div;
            i=j;
            j++;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new MinRoundsToCompleteTasks().minimumRounds(new int []{1,2,1}));
        System.out.println(new MinRoundsToCompleteTasks().minimumRounds(new int []{2,2,3,3,2,4,4,4,4,4}));
        System.out.println(new MinRoundsToCompleteTasks().minimumRounds(new int []{2,3,3}));

    }
}
