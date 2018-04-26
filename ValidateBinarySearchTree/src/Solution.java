/**
 * Created by Yihuan on 2018/4/26.
 */
public class Solution {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    public boolean isValidBST(TreeNode root) {

        return validateNode(root , Long.MIN_VALUE , Long.MAX_VALUE);
    }

    boolean validateNode(TreeNode node , long min ,long max){
        if(node == null)
            return true;

        if(node.val < max && node.val > min){
                return validateNode(node.left , min , node.val) && validateNode(node.right , node.val , max);
        }
        else
            return false;
    }
}
