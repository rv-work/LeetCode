
class Solution {

    class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insert(root.left, val);
        else root.right = insert(root.right, val);
        return root;
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null || p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    private boolean isSame(List<Integer> arr, TreeNode originalRoot) {
        TreeNode newRoot = null;
        for (int num : arr) {
            newRoot = insert(newRoot, num);
        }
        return isSameTree(originalRoot, newRoot);
    }

    int ans = 0;

    private void generatePermutations(int[] nums, List<Integer> current, boolean[] used, TreeNode originalRoot) {
        if (current.size() == nums.length) {
            if (isSame(current, originalRoot)) {
                ans++;
            }
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            
            used[i] = true;
            current.add(nums[i]);
            
            generatePermutations(nums, current, used, originalRoot);
            
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }

    public int numOfWays(int[] nums) {
        TreeNode originalRoot = null;
        for (int num : nums) {
            originalRoot = insert(originalRoot, num);
        }

        boolean[] used = new boolean[nums.length];
        generatePermutations(nums, new ArrayList<>(), used, originalRoot);

        return (ans - 1) % 1_000_000_007;
    }
}