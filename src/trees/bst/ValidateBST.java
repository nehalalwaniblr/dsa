package trees.bst;

import java.util.ArrayList;
import java.util.List;

public class ValidateBST {
    List<Integer> list = new ArrayList<>();

    public boolean isValidBST(TreeNode root) {
//        Iterative approach
//        if(root==null)
//            return false;
//        new ValidateBST().inorderTraversal(root,list);
//        for(int i=1;i<list.size();i++){
//            if(list.get(i-1)>list.get(i))
//                return false;
//        }
//        return true;
        return isValid(root, null, null);
    }

    boolean isValid(TreeNode node, Integer minVal, Integer maxVal) {
        if (node == null)
            return true;
        if ((minVal != null && node.val <= minVal) || (maxVal != null && node.val >= maxVal))
            return false;
        return isValid(node.left, minVal, node.val) && isValid(node.right, node.val, maxVal);
    }

    void inorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left, list);
        list.add(node.val);
        inorderTraversal(node.right, list);
    }

    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        TreeNode right = new TreeNode(6, null, null);
        TreeNode root = new TreeNode(4, left, right);
        new ValidateBST().isValidBST(root);
    }
}
