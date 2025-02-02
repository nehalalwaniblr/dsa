package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null)
            return true;
        if(p==null || q==null || p.val != q.val)
            return false;
        return (isSameTree(p.left,q.left) && isSameTree(p.right,q.right));
    }

    public boolean isSameTreeIterative(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();

        if (p != null)
            queue1.add(p);
        if (q != null)
            queue2.add(q);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            if(node1==null && node2==null)
                continue;
            if(node1==null ||node2==null || node1.val!=node2.val)
                return false;
            queue1.add(node1.left);
            queue1.add(node1.right);
            queue2.add(node2.left);
            queue2.add(node2.right);
        }
//        return true;// this fails the case when first tree is null and other has one element
        return queue1.isEmpty() && queue2.isEmpty();
    }



    //            0
//        1       2
//    3     4         6
    public static void main(String[] args) {
        TreeNode left = new TreeNode(1, new TreeNode(3, null, null), new TreeNode(4, null, null));
        TreeNode right = new TreeNode(2, null, new TreeNode(6, null, null));
        TreeNode root1 = new TreeNode(0, left, right);
//
//
//        TreeNode left = new TreeNode(2, null, null);
//        TreeNode right = null;
//        TreeNode root1 = new TreeNode(1, left, right);
//
        TreeNode right2 = new TreeNode(2, null, null);
        TreeNode left2 = null;
        TreeNode root2 = new TreeNode(1,left2 , right2);


        System.out.println(new SameTree().isSameTree(root1, root2));
    }

}
