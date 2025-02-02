package trees;

import java.util.HashMap;
import java.util.Map;

/*
* Given the root of a binary tree, flatten the tree into a "linked list":

The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.
*
* */


/*
* Solution : Approach
Base Case:

If the current node (root) is nullptr, there is no subtree to flatten, so just return.

Save the Right Subtree:

Store the current node’s right subtree in a temporary pointer, say rightTree. This is because we’re about to overwrite the root->right pointer.

Move the Left Subtree:

Assign root->left to root->right, effectively moving the left subtree to the right side of the current node. Then set root->left = nullptr to ensure we’re creating a linked-list-like structure.

Attach the Old Right Subtree:

Traverse down the newly added right subtree (which was originally the left subtree) until you reach its last node.
Attach the saved rightTree to this last node’s right pointer.

Recursively Flatten the Remaining Tree:

Call flatten on root->right to ensure all subtrees are flattened.

This strategy “partially flattens” the tree at each level by attaching the left subtree to the right and then hanging the old right subtree behind it. Repeatedly doing so in a top-down manner results in a fully flattened tree where each node’s left pointer is null.
* */
public class FlattenBTToLL {
    Map<Integer,TreeNode> map = new HashMap<>();
    public void flatten(TreeNode root) {
        if(root==null)
            return ;
        TreeNode rightSubTree = root.right;
        root.right = root.left;
        root.left=null;
        TreeNode temp = root;
        while(temp.right!=null){
            temp=temp.right;
        }
        temp.right = rightSubTree;
        flatten(root.right);
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

    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(3), new TreeNode(4));
        TreeNode right = new TreeNode(5, null, new TreeNode(6));
        TreeNode root = new TreeNode(1, left, right);
//        System.out.println("**************");
//        new FlattenBTToLL().preOrder(root);
//        System.out.println("**************");
//        new FlattenBTToLL().postOrder(root);
//        System.out.println("**************");
//        new FlattenBTToLL().inOrder(root);
//
        new FlattenBTToLL().flatten(root);

    }
}
