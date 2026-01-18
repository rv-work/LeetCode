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
 */class Solution {
    int max = Integer.MIN_VALUE;

    int maxi(TreeNode root) {
        if (root == null) return 0;

        int left = Math.max(0, maxi(root.left));
        int right = Math.max(0, maxi(root.right));

        int throughMe = root.val + left + right;
        max = Math.max(max, throughMe);
        
        return root.val + Math.max(left, right);
    }
    
    public int maxPathSum(TreeNode root) {
        maxi(root);
        return max;
    }
}


// point is that... hme ek line me jana hai 2 branches nhi ho skte so
// at eavry node hm left side ka nikalte aur right side ka ... fir dekhte ki mere se hokar jane me max ho rha kya ....... and then left and right jha se bhi best tha use lekar ke aur khud ko add krke hm upar jate explore krne ek line me aise krke ..................