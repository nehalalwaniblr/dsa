package trees;

import java.util.LinkedList;
import java.util.Queue;

public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {

        Queue<TreeNode> queue1 = new LinkedList<>();

        if (root != null) {
            queue1.add(root);

        }
        while (!queue1.isEmpty()) {

            TreeNode temp = queue1.remove();
            TreeNode left = temp.left;
            temp.left = temp.right;
            temp.right = left;

            if (temp.left != null) {
                queue1.add(temp.left);
            }
            if (temp.right != null) {
                queue1.add(temp.right);
            }
        }
        return root;
    }
}
