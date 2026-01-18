class Solution {

    void dfs(TreeNode root, int targetSum, List<List<Integer>> ans, List<Integer> path) {
        if (root == null) return;

        path.add(root.val);

        int remaining = targetSum - root.val;

        // if leaf and remaining == 0, add to ans
        if (root.left == null && root.right == null && remaining == 0) {
            ans.add(new ArrayList<>(path));
        }

        dfs(root.left, remaining, ans, path);
        dfs(root.right, remaining, ans, path);

        // backtrack
        path.remove(path.size() - 1);
    }


    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(root, targetSum, ans, new ArrayList<>());
        return ans;
    }
}
