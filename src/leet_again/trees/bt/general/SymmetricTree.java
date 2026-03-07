package leet_again.trees.bt.general;

import java.util.LinkedList;
import java.util.Queue;

/*https://leetcode.com/problems/symmetric-tree/description/?envType=study-plan-v2&envId=top-interview-150*/
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return isSymmetry(root.left, root.right);
    }

    public boolean isSymmetry(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        if (left == null || right == null || left.val != right.val)
            return false;
        return isSymmetry(left.left, right.right) && isSymmetry(left.right, right.left);
    }

    public boolean isSymmetricIterative(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null)
            return true;
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if (left == null && right == null)
                continue;
            ;
            if (left == null)
                return false;
            if (right == null)
                return false;
            if (left.val != right.val)
                return false;
            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);
        }
        return true;
    }

}
