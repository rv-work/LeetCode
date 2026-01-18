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

    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, 1); // base prefix

        return dfs(root, 0L, targetSum, map);
    }

    int dfs(TreeNode root, long currSum, int target, Map<Long, Integer> map) {
        if (root == null) return 0;

        currSum += root.val;

        int count = map.getOrDefault(currSum - target, 0);

        map.put(currSum, map.getOrDefault(currSum, 0) + 1);

        count += dfs(root.left, currSum, target, map);
        count += dfs(root.right, currSum, target, map);

        // backtrack
        map.put(currSum, map.get(currSum) - 1);

        return count;
    }
}
