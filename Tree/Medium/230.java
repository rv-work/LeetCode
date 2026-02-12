class Solution {
    int count = 0;
    int ans = 0;

    void inorder(TreeNode root, int k) {
        if (root == null) return;

        inorder(root.left, k);

        count++;
        if (count == k) {
            ans = root.val;
            return;   // stop further processing
        }

        inorder(root.right, k);
    }

    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return ans;
    }
}
