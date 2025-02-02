package trees;

import java.util.Stack;

/*Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):

BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor. The pointer should be initialized to a non-existent number smaller than any element in the BST.
boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
int next() Moves the pointer to the right, then returns the number at the pointer.
Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the smallest element in the BST.

You may assume that next() calls will always be valid. That is, there will be at least a next number in the in-order traversal when next() is called.

*/
public class BSTIterator {
    TreeNode currentNode = null;
    Stack<TreeNode> stack = new Stack<>();
    public BSTIterator(TreeNode root) {
        currentNode = root;
    }

    /*This is in order traversal using iterative method*/
    public int next() {
        int result = 0;
        while(!stack.isEmpty() || currentNode!=null){
            while(currentNode!=null){
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            TreeNode poppedNode = stack.pop();
            currentNode = poppedNode.right;
            result = poppedNode.val;
            return result;
        }
        return result;
    }

    public boolean hasNext() {
        return currentNode!=null || !stack.isEmpty();
    }

    public static void main(String[] args) {
        TreeNode left = new TreeNode(3, null,null);
        TreeNode right = new TreeNode(15, new TreeNode(9), new TreeNode(20));
        TreeNode root = new TreeNode(7, left, right);
        BSTIterator bstIterator = new BSTIterator(root);
        System.out.println(bstIterator.next());
    }
}
