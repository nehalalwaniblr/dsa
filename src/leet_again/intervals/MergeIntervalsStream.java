package leet_again.intervals;

import java.util.Map;
import java.util.TreeMap;

/*
Extension to MergeIntervals
* Could you adapt this logic to handle a stream of intervals arriving one by one without sorting the entire dataset first?
* */
public class MergeIntervalsStream {
    private TreeMap<Integer, int[]> map = new TreeMap<>();

    public void addInterval(int[] interval) {
        int start = interval[0];
        int end = interval[1];

        // 1. Check left neighbor
        Map.Entry<Integer, int[]> left = map.floorEntry(start);
        if (left != null && left.getValue()[1] >= start) {
            start = Math.min(start, left.getValue()[0]);
            end = Math.max(end, left.getValue()[1]);
            map.remove(left.getKey());
        }

        // 2. Merge with all overlapping right intervals
        Map.Entry<Integer, int[]> right = map.ceilingEntry(start);
        while (right != null && right.getKey() <= end) {
            end = Math.max(end, right.getValue()[1]);
            map.remove(right.getKey());
            right = map.ceilingEntry(start);
        }

        // 3. Insert merged interval
        map.put(start, new int[]{start, end});
    }

    public int[][] getIntervals() {
        return map.values().toArray(new int[map.size()][]);
    }


    public static void main(String[] args) {
        MergeIntervalsStream stream = new MergeIntervalsStream();

//        int[][] inputs = {
//                {2, 6},
//                {1, 3},
//                {8, 10},
//                {15, 18}
//        };

//        int[][] inputs = {
//                {5, 7},    // base
//                {1, 3},    // insert before
//                {2, 6},    // overlap multiple (merge left + middle)
//                {10, 12},  // separate
//                {8, 11},   // overlap right side
//                {4, 9},    // BIG merge across multiple intervals
//                {15, 18},  // separate
//                {17, 20},  // overlap tail
//                {0, 25}    // FULL merge (everything collapses)
//        };

        int[][] inputs = {
                {1,13},
                {8,10},
                {12,15},
                {5,13}
        };


        for (int[] interval : inputs) {
            stream.addInterval(interval);

            System.out.println("After adding: [" + interval[0] + ", " + interval[1] + "]");
            print(stream.getIntervals());
            System.out.println("--------------");
        }
    }

    private static void print(int[][] intervals) {
        for (int[] i : intervals) {
            System.out.print("[" + i[0] + ", " + i[1] + "] ");
        }
        System.out.println();
    }

}
