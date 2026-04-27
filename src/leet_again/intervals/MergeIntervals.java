package leet_again.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*Find overlapping pairs;
* overlap occurs if interval.y>= interval.x i.e. 1,3-2,6
* if over lap occurs calculate the pair and keep it in a variable dont push to result as ther could be more overlaps
* if no overlap simply add to result and mark prev as current
* At end of loop last interval still needs to be added so add it after for loop
* Also, take result as List<int[]> as int[] will be always 2 elements
* also, convert to list.toArray(opp Arrays.asList())
*
* */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
//        use this or below
//        Arrays.sort(intervals,(o1, o2) -> Integer.compare(o1[0],o2[0]));
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int[] prev = intervals[0];
        List<int[]> result = new ArrayList<>();
        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];
            //is overlapping?
            if (prev[1] >= current[0]) {
                prev = new int[]{prev[0], Integer.max(current[1], prev[1])}; //consider example {1,10}{8,9}
            } else {
                result.add(prev);
                prev = current;
            }
        }
        result.add(prev);

        return  result.toArray(new int[][]{});

    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new MergeIntervals().merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})));
        System.out.println(Arrays.deepToString(new MergeIntervals().merge(new int[][]{{1, 4}, {4,5}})));
        System.out.println(Arrays.deepToString(new MergeIntervals().merge(new int[][]{{4, 7}, {1, 4}})));


    }
}

record Pair(int x, int y) {

}