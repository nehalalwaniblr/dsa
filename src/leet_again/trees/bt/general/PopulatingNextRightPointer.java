package leet_again.trees.bt.general;

import java.util.LinkedList;
import java.util.Queue;

/*https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/description/?envType=study-plan-v2&envId=top-interview-150*/
public class PopulatingNextRightPointer {
    public Node connect(Node root) {
        Queue<Node> queue = new LinkedList<>();
        if (root != null)
            queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (i < size - 1) {
                    node.next = queue.peek();
                }
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }

        }
        return root;
    }

    Node levelOrderTraversal(Node root) {

        Queue<Node> queue = new LinkedList<>();
        if (root != null)
            queue.add(root);
        else
            return null;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                System.out.println(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);

            }
        }
        return root;
    }

    public static void main(String[] args) {
        Node left = new Node(2, new Node(4), new Node(5), null);
        Node right = new Node(3, null, new Node(7), null);
        Node root = new Node(1, left, right, null);
//        System.out.println(new PopulatingNextRightPointer().connect(root));

        System.out.println(new PopulatingNextRightPointer().levelOrderTraversal(root));

    }

}


class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};