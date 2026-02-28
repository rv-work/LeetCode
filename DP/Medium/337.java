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
    int[] res(TreeNode root){
        if(root == null) return new int[]{0,0};

        int left[] = res(root.left);
        int right[] = res(root.right);

        int opt[] = new int[2];

        opt[0] = root.val  + left[1] + right[1];
        opt[1] =    Math.max(left[0],left[1]) + Math.max(right[0],right[1]);

        return opt;
    }
    public int rob(TreeNode root) {
        int options[] = res(root); // 0 -> take me , 1 -> do not take me
        return Math.max(options[0] , options[1]);
    }
}