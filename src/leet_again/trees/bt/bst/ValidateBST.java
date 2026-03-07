package leet_again.trees.bt.bst;

import leet_again.trees.bt.general.TreeNode;

public class ValidateBST {
    public boolean isValidBST(TreeNode root) {
        return isValid(root, null, null);
    }

    boolean isValid(TreeNode root, Integer min, Integer max) {
        if (root == null)
            return true;
        if ((min != null && root.val <= min) || (max != null && root.val >= max))
            return false;
        return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
    }

    public static void main(String[] args) {
        TreeNode left = new TreeNode(10, new TreeNode(7, null, new TreeNode(9, new TreeNode(8, null, null), null)), new TreeNode(12));
        TreeNode right = new TreeNode(15, new TreeNode(14, null, null), new TreeNode(17, new TreeNode(16, null, null), null));
        TreeNode root = new TreeNode(13, left, right);
        System.out.println(new ValidateBST().isValidBST(root));
    }
}
