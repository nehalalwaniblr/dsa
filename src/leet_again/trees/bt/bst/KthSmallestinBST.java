package leet_again.trees.bt.bst;

import leet_again.trees.bt.general.TreeNode;

public class KthSmallestinBST {
    int result = 0;
    int count = 0;

    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k );
        return result;
    }

    private void inorder(TreeNode root, int k) {
        if (root == null)
            return;
        inorder(root.left, k);
        count++;
        if (k == count) {
            result = root.val;
        }
        inorder(root.right, k);
    }
}
