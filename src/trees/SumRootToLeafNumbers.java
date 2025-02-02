package trees;

/*
* You are given the root of a binary tree containing digits from 0 to 9 only.

Each root-to-leaf path in the tree represents a number.

For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.

A leaf node is a node with no children.



Example 1:


Input: root = [1,2,3]
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.
* */
public class SumRootToLeafNumbers {
    int result = 0;
    String temp1 = "";
    String temp2 = "";

    public int sumNumbers(TreeNode root) {
        return calculateSum(root,"");
    }

    private int calculateSum(TreeNode root, String sum){

        if (root == null)
            return 0;
        if(root.left==null && root.right==null){
            return Integer.parseInt(sum+root.val+"");
        }

//        result+=calculateSum(root.left,sum+root.val+"");
//        result+=calculateSum(root.right,sum+root.val+"");
        return calculateSum(root.left,sum+root.val+"")+calculateSum(root.right,sum+root.val+"");
    }

    public static void main(String[] args) {
//        System.out.println(new SumRootToLeafNumbers().sumNumbers(new TreeNode(1,new TreeNode(2,null,null),new TreeNode(3,null,null))));
//
//        TreeNode left = new TreeNode(9, new TreeNode(5, null,null), new TreeNode(1,null,null));
////        TreeNode right = new TreeNode(8, new TreeNode(13,null,null), new TreeNode(4, null, new TreeNode(1,null,null)));
//        TreeNode root = new TreeNode(4, left, new TreeNode(0, null,null));
//
//        System.out.println(new SumRootToLeafNumbers().sumNumbers(root));

//        System.out.println(new SumRootToLeafNumbers().sumNumbers(new TreeNode(0,  new TreeNode(1, null,null),null)));
        System.out.println(new SumRootToLeafNumbers().sumNumbers(new TreeNode(1,  new TreeNode(5, null,null),new TreeNode(1, null,new TreeNode(6,null,null)))));



    }
}
