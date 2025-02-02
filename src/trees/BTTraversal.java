package trees;

import java.util.*;

public class BTTraversal {
    public void traversal(TreeNode root) {
        Queue<TreeNode> children = new ArrayDeque<>();
        if (root != null) {
            System.out.println(root.val);
            children.add(root);
        }
        while (!children.isEmpty()) {
            TreeNode node = children.remove();
            if (node.left != null) {
                System.out.println(node.left.val);
                children.add(node.left);
            } else {
                System.out.println("null");
            }
            if (node.right != null) {
                System.out.println(node.right.val);
                children.add(node.right);
            } else {
                System.out.println("null");
            }
        }
    }

    public int heightOfBTTree(TreeNode root) {
        Queue<TreeNode> children = new ArrayDeque<>();
        if (root != null) {
//            System.out.println(root.val);
            children.add(root);
        }
        int height =0;
        while (!children.isEmpty()) {
            int levelNodes = children.size();
            for (int i = 0; i < levelNodes; i++) {
                TreeNode node = children.remove();
                System.out.println(node.val);
                if (node.left != null) {
                    children.add(node.left);
                }
                if (node.right != null) {
                    children.add(node.right);
                }
            }
            height++;
        }
        return height;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {

        // Initialize an empty list to hold the result
        List<List<Integer>> result = new ArrayList<>();

        // If the root is null, return an empty list
        if (root == null) {
            return result;
        }

        // Initialize a queue to hold the nodes in the current level
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        // Traverse the tree level by level
        while (!queue.isEmpty()) {

            // Get the number of nodes in the current level
            int levelSize = queue.size();

            // Initialize a list to hold the nodes in the current level
            List<Integer> levelNodes = new ArrayList<>();

            // Traverse the nodes in the current level
            for (int i = 0; i < levelSize; i++) {
                // Get the first node in the queue
                TreeNode node = queue.remove();
                System.out.println(node.val);
                // Add the node's value to the list of nodes in the current level
                levelNodes.add(node.val);

                // Add the node's children to the queue for the next level
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            // Add the list of nodes in the current level to the result
            result.add(levelNodes);
        }
        // Return the final result
        return result;
    }

    void preOrder(TreeNode root){
        if(root==null)
            return;
        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    void postOrder(TreeNode root){
        if(root==null)
            return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.val);
    }

    void inOrder(TreeNode root){
        if(root==null)
            return;
        inOrder(root.left);
        System.out.println(root.val);
        inOrder(root.right);
    }
//            0
//        1       2
//    3     4         6
    public static void main(String[] args) {
        TreeNode left = new TreeNode(1, new TreeNode(3, null, null), new TreeNode(4, null, null));
        TreeNode right = new TreeNode(2, null, new TreeNode(6, null, null));
        TreeNode root = new TreeNode(0, left, right);
        new BTTraversal().levelOrder(root);
        System.out.println("height = "+new BTTraversal().heightOfBTTree(root));

    }
}
