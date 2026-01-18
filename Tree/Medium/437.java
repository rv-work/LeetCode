class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;

        // DFS-2: count paths starting from current root
        int countFromHere = dfs(root, targetSum);

        // DFS-1: try other starting points
        return countFromHere
             + pathSum(root.left, targetSum)
             + pathSum(root.right, targetSum);
    }

    int dfs(TreeNode node, long remaining) {
        if (node == null) return 0;

        int count = 0;
        if (node.val == remaining) count++;

        count += dfs(node.left, remaining - node.val);
        count += dfs(node.right, remaining - node.val);

        return count;
    }
}
