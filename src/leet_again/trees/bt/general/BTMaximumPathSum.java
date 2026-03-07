package leet_again.trees.bt.general;
//https://leetcode.com/problems/binary-tree-maximum-path-sum/description/?envType=study-plan-v2&envId=top-interview-150
public class BTMaximumPathSum {
    int result;
    public int maxPathSum(TreeNode root) {
        result = Integer.MIN_VALUE;
        solve(root);
        return result;
    }

    private int solve(TreeNode root) {
        if(root==null)
            return 0;
        int left =  solve(root.left);
        int right = solve(root.right);
        int answer_with_root_left_right_cant_explore_further = root.val+left+right; //------1st case

        int answer_with_either_left_or_right_with_root = Math.max(left,right)+root.val; //-----2nd case

        int answer_with_just_root = root.val; //-----3rd case
        result = Math.max(Math.max(result, answer_with_root_left_right_cant_explore_further), Math.max(answer_with_either_left_or_right_with_root,answer_with_just_root));


        //what to return? we can return anything apart from first case coz it includes root and with this there is no path possible

        return Math.max(answer_with_either_left_or_right_with_root, answer_with_just_root);

    }
}
