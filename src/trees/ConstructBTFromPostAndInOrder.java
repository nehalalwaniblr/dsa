package trees;

import java.util.HashMap;
import java.util.Map;

public class ConstructBTFromPostAndInOrder {
    private Map<Integer,Integer> inOrderIndexMap = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for(int i=0;i<inorder.length;i++){
            inOrderIndexMap.put(inorder[i],i);
        }
        return constructTree(0,inorder.length-1,postorder,0,postorder.length-1);
    }
    TreeNode constructTree( int inLeft, int inRight, int[] postorder, int pLeft, int pRight){
        if(inLeft>inRight || pLeft>pRight)
            return null;
        int rootValue = postorder[pRight];
        int inOrderIndex = inOrderIndexMap.get(rootValue);
        TreeNode node = new TreeNode(rootValue,null,null);
        int leftSize =  inOrderIndex - inLeft;
        int rightSize =  inRight - inOrderIndex;
        node.left = constructTree(inLeft,inOrderIndex-1, postorder,pLeft,pLeft+leftSize-1);
        node.right = constructTree(inOrderIndex+1,inRight,postorder,pRight-rightSize,pRight-1);
        return node;
    }


    public static void main(String[] args) {
        TreeNode root = new ConstructBTFromPostAndInOrder().buildTree(new int[]{5,1,9,2,3,15,20,7},new int[]{5,1,2,9,15,7,20,3});
        System.out.println(root);
    }

}
