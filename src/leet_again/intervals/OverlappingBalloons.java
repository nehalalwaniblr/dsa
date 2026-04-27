package leet_again.intervals;

import java.util.Arrays;
import java.util.Comparator;

/*
* consider below intervals:
*
* [1,6],[2,8],[7,12],[10,16]
* ----1-----------6---------
* ------2-----------------8
* in this if you see the overlap interval where the arrow should hit is b/w 2 and 6 i.e. max(x) and min(y)
* once you get this you'll compare it with next interval and so on.
* Note: result=1 initially; why? coz there will be atleast 1 arrow needed to burst the balloon
* */
public class OverlappingBalloons {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparing(o -> o[0]));
        int result = 1;
        int[] prev = points[0];
        for (int i = 1; i < points.length; i++) {
            int[] current = points[i];
            //check overlap
            if(prev[1]>=current[0]){
                prev[0] = Math.max(prev[0], current[0]);
                prev[1] = Math.min(prev[1], current[1]);
            }else {
                result++;
                prev = current;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new OverlappingBalloons().findMinArrowShots(new int[][]{{10,16},{2,8},{1,6},{7,12}}));
        System.out.println(new OverlappingBalloons().findMinArrowShots(new int[][]{{1,2},{3,4,},{5,6},{7,8}}));
        System.out.println(new OverlappingBalloons().findMinArrowShots(new int[][]{{1,2},{2,3},{3,4},{4,5}}));

    }
}
