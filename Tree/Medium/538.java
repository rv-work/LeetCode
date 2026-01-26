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

// class Solution {
//     int sum = 0;

//     void GT(TreeNode root){
//         if(root == null) return;
//         GT(root.right);
//         sum += root.val;
//         root.val = sum;
//         GT(root.left); 
//     }

//     public TreeNode convertBST(TreeNode root) {
//         GT(root);
//         return root;
//     }
// }

// not better but another way of doing.............

class Solution {

    // STEP 1: store inorder
    void inorder(TreeNode root, List<Integer> arr) {
        if (root == null) return;
        inorder(root.left, arr);
        arr.add(root.val);
        inorder(root.right, arr);
    }

    // STEP 3: overwrite BST inorder-sequence me
    void update(TreeNode root, List<Integer> arr, int[] idx) {
        if (root == null) return;
        update(root.left, arr, idx);
        root.val = arr.get(idx[0]++);
        update(root.right, arr, idx);
    }

    public TreeNode convertBST(TreeNode root) {
        // STEP 1: inorder extraction
        List<Integer> arr = new ArrayList<>();
        inorder(root, arr);

        // STEP 2: convert to suffix sum
        for (int i = arr.size() - 2; i >= 0; i--) {
            arr.set(i, arr.get(i) + arr.get(i + 1));
        }

        // STEP 3: write back values to BST
        int[] idx = {0};  // mutable index for inorder
        update(root, arr, idx);

        return root;
    }
}
