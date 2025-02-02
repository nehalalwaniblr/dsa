import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();

        for(int i=1;i<=intervals.length;i++){
            int j=i;
            while(j< intervals.length && intervals[j-1][1]>=intervals[j][0]){
                j++;
            }
            int[] array= new int[2];
            array[0]= Math.min(intervals[i-1][0],intervals[j-1][0]);
            array[1]= Math.max(intervals[j-1][1], intervals[i-1][1]);
            result.add(array);
            i=j;
        }
        return result
                .toArray(int[][]::new);
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(new MergeIntervals().merge(new int[][]{{1,3},{2,6},{8,10},{15,18}})));
        System.out.println(Arrays.toString(new MergeIntervals().merge(new int[][]{{1,4},{0,4}})));

    }
}
