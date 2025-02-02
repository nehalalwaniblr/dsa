import java.util.Arrays;
import java.util.stream.IntStream;

public class Candies {
    public int candy(int[] ratings) {
        int totalCandy =0;
        int candies[] = new int[ratings.length];
        Arrays.fill(candies,1);
        for(int i=0;i<ratings.length;i++){
            int left=Integer.MAX_VALUE; int right=Integer.MAX_VALUE;
            if(i+1<ratings.length){
                right=ratings[i+1];
            }
            if(i-1>=0){
                left =ratings[i-1];
            }
            if(ratings[i]>left || ratings[i]>right){
                candies[i]+=1;
            }
        }
        int sum = IntStream.of(candies).sum();
        return sum;
    }
}
