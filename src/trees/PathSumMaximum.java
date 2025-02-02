package trees;

/*
* A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. \
* Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any non-empty path.



Example 1:


Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.

* */
public class PathSumMaximum {
    int result =Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        pathSum(root);
        return result;
    }

    public int pathSum(TreeNode root){
        if(root==null)
            return 0;
        int left = Math.max(0,pathSum(root.left));
        int right = Math.max(0,pathSum(root.right));
        int sum = root.val+left+right;
        result = Math.max(result, sum);
//        Check the diameter of a tree to relate better
        return root.val +Math.max(left,right);
    }

    public static void main(String[] args) {
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(20, new TreeNode(15,null,null), new TreeNode(7, null, null));
        TreeNode root = new TreeNode(-10, left, right);
        System.out.println(new PathSumMaximum().maxPathSum(root));

    }
}
