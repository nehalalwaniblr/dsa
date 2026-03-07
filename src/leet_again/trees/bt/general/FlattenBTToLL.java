package leet_again.trees.bt.general;

//https://leetcode.com/problems/flatten-binary-tree-to-linked-list/?envType=study-plan-v2&envId=top-interview-150
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FlattenBTToLL {
    List<Integer> result = new ArrayList<>();


    public void flatten(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        if (root != null)
            stack.add(root);
        else
            return;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
            node.left=null;
            if(!stack.isEmpty()){
                node.right = stack.peek();
            }
        }
    }



    public List<Integer> preorderTraversal(TreeNode root){
        if (root != null)
            preOrder(root);
        else
            return result;

        return result;
    }

    private void preOrder(TreeNode root) {
        if(root==null)
            return;
        result.add(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    public List<Integer> preorderTraversal2(TreeNode root){
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null)
            stack.add(root);
        else
            return result;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            System.out.println(node.val);
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
        return result;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


}

