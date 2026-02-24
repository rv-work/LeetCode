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
    int sum = 0;
    int mask = 0;

    void res(TreeNode root) {
        if (root.left == null && root.right == null) {
            mask += root.val;
            sum += mask;
            mask -= root.val;
            return ;
        }

        int val = root.val;
        mask += val;
        mask <<= 1;
        sumRootToLeaf(root.left);
        sumRootToLeaf(root.right);
        mask >>= 1;
        mask -= val;
    }

    public int sumRootToLeaf(TreeNode root) {
        if(root == null) return 0;
        res(root);
        return sum;
    }
}