import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0)
            return new int[][] {newInterval};
        int start = 0;
        int end = intervals.length - 1;
        List<int[]> intervalsList = new ArrayList<>(Arrays.asList(intervals));

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (intervals[mid][0] <= newInterval[0]) {
                start=mid+1;
            } else {
                end = mid - 1;
            }
        }
        intervalsList.add(start, newInterval);
        int[][] intervalsArray = intervalsList.toArray(new int[intervalsList.size()][]);

        return merge(intervalsArray);
    }

    int[][] merge(int[][] intervals) {
        List<int[]> merged = new ArrayList<>();
        int[] prev = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval[0] <= prev[1]) {
                prev[1] = Math.max(prev[1], interval[1]);
            } else {
                merged.add(prev);
                prev = interval;
            }
        }

        merged.add(prev);

        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        new InsertInterval().insert(new int[][]{{1,3},{6,9}}, new int[]{2,5});
    }
}
