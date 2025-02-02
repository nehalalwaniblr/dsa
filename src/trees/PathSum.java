package trees;
/*
* Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.

A leaf is a node with no children.*/
public class PathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
//        if(root==null && targetSum==0)
//            return false;
//        return hasPathSum2(root,targetSum);
        if(root==null)
            return false;
        if(root.left==null && root.right==null)
            return root.val==targetSum;
        targetSum-=root.val;
        return hasPathSum(root.left,targetSum) || hasPathSum(root.right,targetSum);

    }
    public boolean hasPathSum2(TreeNode root, int targetSum) {
        if(targetSum==0)
            return true;
        if(root==null || targetSum<0)
            return false;
//        if(targetSum<0)
//            hasPathSum(root.right,targetSum);
        return hasPathSum2(root.left,targetSum-root.val) || hasPathSum(root.right,targetSum-root.val) ;

    }

    public static void main(String[] args) {
        TreeNode left = new TreeNode(4, new TreeNode(11, new TreeNode(7,null,null), new TreeNode(2,null,null)), null);
        TreeNode right = new TreeNode(8, new TreeNode(13,null,null), new TreeNode(4, null, new TreeNode(1,null,null)));
        TreeNode root = new TreeNode(5, left, right);
        System.out.println(new PathSum().hasPathSum(root,22));

        System.out.println(new PathSum().hasPathSum(new TreeNode(1,new TreeNode(2,null,null),null),0));
    }
}
