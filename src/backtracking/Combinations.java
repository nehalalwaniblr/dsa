package backtracking;

import java.util.ArrayList;
import java.util.List;

/*Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].

You may return the answer in any order.



Example 1:

Input: n = 4, k = 2
Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
Explanation: There are 4 choose 2 = 6 total combinations.
Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
Example 2:

Input: n = 1, k = 1
Output: [[1]]
Explanation: There is 1 choose 1 = 1 total combination.


Constraints:

1 <= n <= 20
1 <= k <= n
*/
public class Combinations {
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        solve(1,n, k, new ArrayList<>());
        System.out.println(result);
        return result;
    }

    void solve(int start,int n, int k, List<Integer> combinations) {
        if (k == 0){
            result.add(new ArrayList<>(combinations)); // ✅ Add a copy to avoid reference issues
            return;
        }
        if(start>n)
            return;

        combinations.add(start);
        solve(start + 1,n, k - 1, combinations);
        combinations.remove(combinations.size() - 1);
        solve(start + 1,n, k, combinations);
    }

    /*With for loop+backtracking*/
    void solve2(int start, int n, int k, List<Integer> combinations) {
        if (k == 0) {
            result.add(new ArrayList<>(combinations)); // ✅ Add a copy to avoid reference issues
            return;
        }

        for (int i = start; i <= n; i++) {
            combinations.add(i);
            solve2(i + 1, n, k - 1, combinations); // ✅ Corrected recursion step
            combinations.remove(combinations.size() - 1);
        }
    }

    public static void main(String[] args) {
        new Combinations().combine(3, 2);
    }
}
