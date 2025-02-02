package trees;

public class LcaOfTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root ==null || root==p || root==q)
            return root;
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if(left!=null && right !=null)
            return root;
        return left!=null?left:right;

    }

    public static void main(String[] args) {
        TreeNode q = new TreeNode(4);
        TreeNode a = new TreeNode(5, new TreeNode(6), new TreeNode(2,new TreeNode(7),q));
        TreeNode b = new TreeNode(1, new TreeNode(0), new TreeNode(8));

        TreeNode left = a;
        TreeNode right = b;
        TreeNode root = new TreeNode(3, left, right);
        TreeNode lca = new LcaOfTree().lowestCommonAncestor(root,a,q);
        System.out.println(lca!=null?lca.val:null);
    }
}
