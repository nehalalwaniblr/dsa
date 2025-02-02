package trees;

public class HeightOfBT {
    public int heightOfBT(TreeNode root){
        if(root==null)
            return 0;
        int left = heightOfBT(root.left);
        int right = heightOfBT(root.right);
        return Math.max(left,right)+1;
    }
}
