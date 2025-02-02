package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);

        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            if (node1 == null && node2 == null)
                continue;
            if (node1 == null || node2 == null ||
                    node1.val != node2.val)
                return false;
            queue.add(node1.left);
            queue.add(node2.right);
            queue.add(node1.right);
            queue.add(node2.left);
        }
        return true;
    }

    public boolean isSymmetric2(TreeNode root) {
        if(root==null)
            return true;
        return isMirrored(root.left,root.right);

    }

    private boolean isMirrored(TreeNode left, TreeNode right){
        if(left==null && right==null)
            return true;
        if(left==null || right ==null || left.val!=right.val)
            return false;
        return isMirrored(left.left,right.right) && isMirrored(left.right,right.left);
    }
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(3, null, null), new TreeNode(4, null, null));
        TreeNode right = new TreeNode(2, new TreeNode(4, null, null), new TreeNode(3, null, null));
        TreeNode root1 = new TreeNode(1, left, right);
//
//
//        TreeNode left = new TreeNode(2, null, null);
//        TreeNode right = null;
//        TreeNode root1 = new TreeNode(1, left, right);
//
//        TreeNode right2 = new TreeNode(2, null, null);
//        TreeNode left2 = null;
//        TreeNode root2 = new TreeNode(1,left2 , right2);


        System.out.println(new SymmetricTree().isSymmetric2(root1));
    }
}

