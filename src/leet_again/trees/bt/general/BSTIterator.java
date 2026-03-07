package leet_again.trees.bt.general;
//https://leetcode.com/problems/binary-search-tree-iterator/description/?envType=study-plan-v2&envId=top-interview-150

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class BSTIterator {
    TreeNode currentNode;
    Stack<TreeNode> stack = new Stack<>();

    public BSTIterator(TreeNode root) {
        this.currentNode = root;
    }

    public int next() {
        int result = 0;
        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            TreeNode poppedNode = stack.pop();
            currentNode = poppedNode.right;
            result =  poppedNode.val;
            return result;

        }
        return result;
    }

    public boolean hasNext() {
        return currentNode != null && currentNode.right != null;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null)
            return result;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            result.add(root.val);
            root = root.right;

        }
        return result;
    }
    public static void main(String[] args) {
        TreeNode left = new TreeNode(3, null,null);
        TreeNode right = new TreeNode(15, new TreeNode(9), new TreeNode(20));
        TreeNode root = new TreeNode(7, left, right);
        BSTIterator bstIterator = new BSTIterator(root);
        System.out.println(bstIterator.next());
    }
}

