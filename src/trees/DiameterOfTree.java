package trees;

public class DiameterOfTree {
    int diameter=0;
    public int diameterOfBinaryTree(TreeNode root) {
        heightOfBT(root);
        return diameter;
    }

    public int heightOfBT(TreeNode root){
        if(root==null)
            return 0;
        int left =diameterOfBinaryTree(root.left);
        int right = diameterOfBinaryTree(root.right);
        diameter =  Math.max(diameter,left+right);
        return Math.max(left,right)+1;
    }

}
