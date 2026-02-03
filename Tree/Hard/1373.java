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
    int maxSum = 0;

    public int maxSumBST(TreeNode root) {
        helper(root);
        return maxSum;
    }

    /**
     * Returns an int[] of size 4 for each subtree:
     * [0] -> 1 if subtree is BST, else 0
     * [1] -> minimum value in this subtree
     * [2] -> maximum value in this subtree
     * [3] -> sum of this subtree (only valid if BST)
     *
     * If subtree is NOT a BST, only index [0] matters.
     */
    private int[] helper(TreeNode root) {

        // Base case:
        // Null subtree is considered a BST with:
        // min = +∞ (so parent comparisons work)
        // max = -∞
        // sum = 0
        if (root == null) {
            return new int[]{1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }

        // Post-order: Evaluate left & right subtrees first
        int[] left  = helper(root.left);
        int[] right = helper(root.right);

        int[] curr = new int[4];

        // Check BST conditions:
        // 1) Left subtree must be BST
        // 2) Right subtree must be BST
        // 3) root.val must be > max(left)
        // 4) root.val must be < min(right)
        if (left[0] == 1 && right[0] == 1 &&
            root.val > left[2] && root.val < right[1]) {

            // Current subtree IS a valid BST
            curr[0] = 1;
            curr[1] = Math.min(root.val, left[1]);     // New min
            curr[2] = Math.max(root.val, right[2]);    // New max
            curr[3] = left[3] + right[3] + root.val;   // Subtree sum

            // Update global maximum sum of any BST subtree
            maxSum = Math.max(maxSum, curr[3]);
        } 
        else {
            // Current subtree is NOT a BST
            curr[0] = 0;
            // No need to set min/max/sum, they won't be used by parent
        }

        return curr;
    }
}












// ....................we need 4 things at every node... from its childs....................

// 1. Are you a BST?
// Description: A boolean flag indicating if the entire subtree rooted at the current node satisfies all BST properties.......
// The "Why": If either the left or right child is not a BST, the parent node cannot form a larger BST, even if its own value is correctly positioned.



// 2. What is your total sum?
// Description: The combined sum of all node values within the current subtree.......
// The "Why": We need this to calculate the potential sum of the parent’s BST ($Sum_{Left} + Sum_{Right} + Node.val$) and to track the global maximum sum found so far.



// 3. What is your minimum value?Description: The smallest value present in the entire current subtree.....
// The "Why": A parent node needs this to verify its Right Subtree property; the parent’s value must be strictly smaller than the minimum value of its right childs entire subtree.

// 4. What is your maximum value?
// Description: The largest value present in the entire current subtree.......
// The "Why": A parent node needs this to verify its Left Subtree property; the parent’s value must be strictly larger than the maximum value of its left child's entire subtree.