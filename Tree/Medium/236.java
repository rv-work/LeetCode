/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
         if(root == null) return null;

         TreeNode left = lowestCommonAncestor(root.left , p , q);
         TreeNode right = lowestCommonAncestor(root.right , p , q);

        if((left == p && right == q) || (left == q && right == p)) return root;
        if(left == null && right == null) {
            if(root == p || root == q) return root;
            return null;
        }

        if(left == null ){
           if(right == p && root == q || right == q && root == p) return root;
           return right;
        } else {
            if(left == p && root == q || left == q && root == p) return root;
           return left;
        }
    }
}









class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) return null;
        if (root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // p and q are on different sides → LCA found
        if (left != null && right != null) return root;

        // Otherwise p and q are either both left or both right
        if (left != null) return left;
        return right;
    }
}
