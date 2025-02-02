package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZigzagTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        int level = 0;
        if (root == null)
            return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelNodes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (level % 2 == 0) {
                    levelNodes.add(node.val);
                } else
                    levelNodes.add(0, node.val);

                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            level++;
            result.add(levelNodes);
        }
        return result;
    }

    public static void main(String[] args) {
//        TreeNode left = new TreeNode(9, null, null);
//        TreeNode right = new TreeNode(20, new TreeNode(15), new TreeNode(7));
//        TreeNode root = new TreeNode(3, left, right);

        TreeNode left = new TreeNode(2, new TreeNode(4), null);
        TreeNode right = new TreeNode(3, null, new TreeNode(5));
        TreeNode root = new TreeNode(1, left, right);
        new ZigzagTraversal().zigzagLevelOrder(root);
    }
}
