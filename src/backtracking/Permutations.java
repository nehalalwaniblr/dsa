package backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutations {
    List<List<Integer>> result;
    Set<Integer> set = new HashSet<>();

    public List<List<Integer>> permute(int[] nums) {
        result = new ArrayList<>();
        solve(nums, nums.length, new ArrayList<>());
        return result;
    }

    void solve(int[] nums, int n, List<Integer> temp) {
        if (temp.size() == n) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!set.contains(nums[i])) {
                temp.add(nums[i]);
                set.add(nums[i]);
                solve(nums, n, temp);
                temp.remove(temp.size() - 1);
                set.remove(nums[i]);
            } else {
                set.add(nums[i]);
            }
        }
    }

    public static void main(String[] args) {
        new Permutations().permute(new int[]{1, 2, 3});
    }
}
