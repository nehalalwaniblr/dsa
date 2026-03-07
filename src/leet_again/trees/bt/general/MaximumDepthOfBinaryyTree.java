package leet_again.trees.bt.general;

/*https://leetcode.com/problems/maximum-depth-of-binary-tree/description/?envType=study-plan-v2&envId=top-interview-150*/
public class MaximumDepthOfBinaryyTree {
    public int maxDepth(TreeNode root) {
        return findMaxDepth(root);
    }

    int findMaxDepth(TreeNode root) {
        if (root == null)
            return 0;
        int left = 1+findMaxDepth(root.left);
        int right = 1+findMaxDepth(root.right);
        return Integer.max(left,right);
    }

    public static void main(String[] args) {
       TreeNode left = new TreeNode(9, null,null);
        TreeNode right = new TreeNode(20, new TreeNode(15, null, null), new TreeNode(7, null, null));
        TreeNode root = new TreeNode(3, left, right);
        System.out.println(new MaximumDepthOfBinaryyTree().maxDepth(root));
    }


}
