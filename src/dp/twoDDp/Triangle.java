package dp.twoDDp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* Given a triangle array, return the minimum path sum from top to bottom.

For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.



Example 1:

Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
Output: 11
Explanation: The triangle looks like:
   2
  3 4
 6 5 7
4 1 8 3
The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
Example 2:

Input: triangle = [[-10]]
Output: -10


Constraints:

1 <= triangle.length <= 200
triangle[0].length == 1
triangle[i].length == triangle[i - 1].length + 1
-104 <= triangle[i][j] <= 104


Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?
* */
public class Triangle {
    /*Here we are using bottom up approach
                        2
                      3 4
                     6 5 7
                    4 1 8 3
    *
    for the above try to fill the triangle from bottom like
    min(4+6, 1+6) will go the position of 6(2,0) in the dp array
    min(1+5,8+5) will go the position of 5(2,1) in the dp array and so on. our dp triangle will look like; return dp[0][0]
          11
        9  10
      7  6  10
    4  1  8  3
Follow up: since we only need the previous(from the bottom) row we can reduce space complexity from O(n2) to O(n); Time complexity is O(n2)

    */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp= new int[n+1][n+1];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],0);
        }
        for(int i=n;i>=0;i--){
            for(int j=0;j<i;j++){
                dp[i-1][j] = Math.min((dp[i][j]+ triangle.get(i-1).get(j)), (dp[i][j+1]+triangle.get(i-1).get(j)));
            }
        }
        return dp[0][0];
    }

    /*Reducing space complexity*/
    public int minimumTotal2(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] front = new int[n];
        for(int j =0 ; j<n ; j++){
            front[j]=triangle.get(n-1).get(j);
        }
        for(int i=n-2 ; i>=0 ; i--){
            int []curr=new int[i+1];
            for(int j=i ; j>=0 ; j--){
                int d = triangle.get(i).get(j) + front[j];
                int dg = triangle.get(i).get(j) + front[j+1];
                curr[j]=Math.min(d,dg);
            }
            front=curr;
        }
        return front[0];
    }

    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();

        list.add(Arrays.asList(2));
        list.add(Arrays.asList(3, 4));
        list.add(Arrays.asList(6, 5, 7));
        list.add(Arrays.asList(4, 1, 8, 3));

        System.out.println(new Triangle().minimumTotal2(list));
    }
}
