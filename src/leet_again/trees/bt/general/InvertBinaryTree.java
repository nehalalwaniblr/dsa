package leet_again.trees.bt.general;

public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if (root==null)
            return root;
        return invert(root);

    }

    TreeNode invert(TreeNode root) {
        if (root == null)
            return null;

        TreeNode temp = root.left;
        root.left.val = root.right.val;
        root.right = temp;

        invert(root.left);
        invert(root.right);
        return root;
    }

}
