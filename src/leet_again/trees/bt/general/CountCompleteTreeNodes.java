package leet_again.trees.bt.general;
//https://leetcode.com/problems/count-complete-tree-nodes/description/?envType=study-plan-v2&envId=top-interview-150
public class CountCompleteTreeNodes {
    public int countNodes(TreeNode root) {
        if(root==null)
            return 0;
        int left = 1+leftHeight(root.left);
        int right = 1+rightHeight(root.right);
        if(left==right)
            return (1 << left) -1;
        return 1+countNodes(root.left)+countNodes(root.right);

    }

    private int rightHeight(TreeNode right) {
        if(right==null)
            return  0;
        return 1+rightHeight(right.right);
    }

    private int leftHeight(TreeNode left) {
        if(left==null)
            return  0;
        return 1+leftHeight(left.left);
    }
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(4, null, null), new TreeNode(5, null, null));
        TreeNode right = new TreeNode(3, new TreeNode(6, null, null), null);
        TreeNode root = new TreeNode(1, left, right);
        System.out.println(new CountCompleteTreeNodes().countNodes(root));
    }
}
