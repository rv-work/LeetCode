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

    private TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);

        if (val < root.val) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }

        return root;
    }

    public TreeNode bstFromPreorder(int[] preorder) {

        TreeNode root = new TreeNode(preorder[0]);

        for (int i = 1; i < preorder.length; i++) {
            insert(root, preorder[i]);  
        }

        return root;
    }
}











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

    private void insert(TreeNode root, int val) {
        TreeNode curr = root;

        while (true) {
            if (val < curr.val) {
                // go left
                if (curr.left == null) {
                    curr.left = new TreeNode(val);
                    break;
                } else {
                    curr = curr.left;
                }
            } else {
                // go right
                if (curr.right == null) {
                    curr.right = new TreeNode(val);
                    break;
                } else {
                    curr = curr.right;
                }
            }
        }
    }

    public TreeNode bstFromPreorder(int[] preorder) {

        TreeNode root = new TreeNode(preorder[0]);

        for (int i = 1; i < preorder.length; i++) {
            insert(root, preorder[i]);
        }

        return root;
    }
}









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
    int i = 0;

    public TreeNode bstFromPreorder(int[] preorder) {
        return build(preorder, Integer.MAX_VALUE);
    }

    private TreeNode build(int[] pre, int bound) {
        if (i == pre.length || pre[i] > bound) return null;

        TreeNode root = new TreeNode(pre[i++]);

        root.left = build(pre, root.val);

        root.right = build(pre, bound);

        return root;
    }
}
