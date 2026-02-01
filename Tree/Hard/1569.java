
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

















class Solution {
    long MOD = 1000000007;

    private long solve(List<Integer> arr, long[][] nCr) {
        int n = arr.size();
        if (n <= 2) return 1; 

        int root = arr.get(0);

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            if (arr.get(i) < root) left.add(arr.get(i));
            else right.add(arr.get(i));
        }

        long leftWays = solve(left, nCr);
        long rightWays = solve(right, nCr);

        // Choose positions of left or right subtree nodes among total children sirf ek ke liye hi lenge dusre apne aap adjust honge .... sirf ek part ko hi arrange krenge ..
        long waysToInterleave = nCr[n - 1][left.size()];

        return (((waysToInterleave * leftWays) % MOD) * rightWays) % MOD;
    }

    public int numOfWays(int[] nums) {
        int n = nums.length;

        long[][] nCr = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            nCr[i][0] = nCr[i][i] = 1;
            for (int j = 1; j < i; j++) {
                nCr[i][j] = (nCr[i - 1][j - 1] + nCr[i - 1][j]) % MOD;
            }
        }

        List<Integer> arr = new ArrayList<>();
        for (int x : nums) arr.add(x);

        return (int)((solve(arr, nCr) - 1 + MOD) % MOD);
    }
}
