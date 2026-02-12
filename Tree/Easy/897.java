/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    TreeNode ans = null;
    TreeNode move = null;

    void inorder(TreeNode root){
        if(root == null) return;

        inorder(root.left);

        root.left = null;

        if(ans == null) ans = root;

        else move.right = root; //  ( first time nhi chalega kyunki ans == null hai )


        move = root; // moving pointer to 

        inorder(root.right);

    }
    public TreeNode increasingBST(TreeNode root) {
        inorder(root);
        return ans;
    }
}