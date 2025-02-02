package trees;

import java.util.LinkedList;
import java.util.Queue;

/*
* Given a binary tree

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.



Example 1:


Input: root = [1,2,3,4,5,null,7]
Output: [1,#,2,3,#,4,5,7,#]
Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
Example 2:

Input: root = []
Output: []


Constraints:

The number of nodes in the tree is in the range [0, 6000].
-100 <= Node.val <= 100
* */
public class PopulatingNextRightPointer {
    public Node connect(Node root) {
        Node head = root;
        while(head!=null){
            Node pre = new Node(0);
            Node tmp = pre;
            while(head!=null){
                if(head.left!=null){
                    pre.next = head.left;
                    pre = pre.next;
                }
                if(head.right!=null){
                    pre.next = head.right;
                    pre = pre.next;
                }
                head = head.next;
            }
            head = tmp.next;
        }
        return root;
    }


    public Node connect2(Node root) {
        Queue<Node> queue = new LinkedList<>();
        if(root!=null){
            queue.add(root);
//            root.next=null;
        }
        else
            return null;
        while (!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                Node node = queue.remove();
                if(i<size-1){
                    node.next = queue.peek();
                }
                if(node.left!=null)queue.add(node.left);
                if(node.right!=null)queue.add(node.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Node left = new Node(2, new Node(4), new Node(5),null);
        Node right = new Node(3, null, new Node(7),null);
        Node root = new Node(-1, left, right,null);
        System.out.println(new PopulatingNextRightPointer().connect(root));
    }
}
