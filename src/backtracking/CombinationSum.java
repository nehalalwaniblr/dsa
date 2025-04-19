package backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
return null;
    }

    void solve(int start, int n, int target, int[] candidates, List<Integer> combinations, int currentSum) {
        if (currentSum == target) {
            result.add(new ArrayList<>(combinations));
            return;
        }
        if (currentSum > target)
            return;
        if (candidates[start] + currentSum < target) {
            combinations.add(candidates[start]);
            solve(start, n, target, candidates, combinations, currentSum + candidates[start]);
        }else{
            combinations.add(candidates[start]);
            solve(start+1, n, target, candidates, combinations, currentSum + candidates[start]);
        }
        combinations.remove(combinations.size()-1);
        solve(start, n, target, candidates, combinations, currentSum + candidates[start]);
    }
}
