package leet_again.trees.bt.general;

public class PathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return checkPath(root,  targetSum);
    }

    private boolean checkPath(TreeNode root, int targetSum) {
        if(root==null)
            return false;
        if(root.left==null && root.right==null )
            return targetSum == root.val;
        targetSum-=root.val;
        return checkPath(root.left, targetSum) || checkPath(root.right, targetSum);

    }

    public static void main(String[] args) {
        TreeNode left = new TreeNode(4, new TreeNode(11, new TreeNode(7,null,null), new TreeNode(2,null,null)), null);
        TreeNode right = new TreeNode(8, new TreeNode(13,null,null), new TreeNode(4, null, new TreeNode(1,null,null)));
        TreeNode root = new TreeNode(5, left, right);
        System.out.println(new PathSum().hasPathSum(root,22));

        System.out.println(new PathSum().hasPathSum(new TreeNode(1,new TreeNode(2,null,null),null),0));
    }
}
