package leet_again.trees.bt.general;

public class SumRootToLeafNumbers {
    public int sumNumber2s(TreeNode root) {
        return sumRootToPath2(root, "");
    }

    private int sumRootToPath2(TreeNode root,  String sum) {
        if(root==null)
            return 0;
        if(root.left==null && root.right==null){
            return Integer.parseInt(sum+root.val+"");
        }
        int leftSum = sumRootToPath2(root.left,sum+root.val+"");
        int rightSum =  sumRootToPath2(root.right,sum+root.val+"");
        return leftSum+rightSum;
    }

    public int sumNumbers(TreeNode root) {
        return sumRootToPath(root, 0);
    }

    private int sumRootToPath(TreeNode root,  int path) {
        if(root==null)
            return 0;
        path =path*10+root.val;
        if(root.left==null && root.right==null){
            return path+root.val;
        }
        int leftSum = sumRootToPath(root.left,path);
        int rightSum =  sumRootToPath(root.right,path);
        return leftSum+rightSum;
    }

}
