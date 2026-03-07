package leet_again.trees.bt.general;

public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return isSame(p,q);
    }

    private boolean isSame(TreeNode p, TreeNode q) {
        if(p==null && q==null)
            return true;
        if(p == null)
            return false;
        if(q == null)
            return false;

        if(p.val!=q.val)
            return false;
        return isSame(p.left, q.left) && isSame(p.right, q.right);
    }

    public static void main(String[] args) {
        TreeNode left = new TreeNode(9, null,null);
        TreeNode right = new TreeNode(20, new TreeNode(15, null, null), new TreeNode(7, null, null));
        TreeNode root1 = new TreeNode(3, left, right);


         left = new TreeNode(9, null,null);
         right = new TreeNode(20, new TreeNode(15, null, null), new TreeNode(7, null, null));
        TreeNode root2 = new TreeNode(3, left, right);

        System.out.println(new SameTree().isSameTree(root1, root2));
    }
}
