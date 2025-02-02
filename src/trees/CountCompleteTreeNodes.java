package trees;

public class CountCompleteTreeNodes {
int result =0;
    public int countNodes(TreeNode root) {
        if(root==null)
            return 0;
        return countNodes2(root,0);
    }


    public int countNodes2(TreeNode root, int count) {
        result+= (int) Math.pow(2,count);

        if(root==null)
            return result-1;
        return countNodes2(root.right,count+1);
//        return result;
    }

    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(4, null,null), new TreeNode(5,null,null));
        TreeNode right = new TreeNode(3, new TreeNode(6,null,null), null);
        TreeNode root = new TreeNode(1, left, right);
        System.out.println(new CountCompleteTreeNodes().countNodes(root));
    }
}
