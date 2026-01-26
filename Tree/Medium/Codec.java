/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // ---------------------
    // SERIALIZATION
    // ---------------------
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("null,");
            return;
        }

        sb.append(node.val).append(",");
        buildString(node.left, sb);
        buildString(node.right, sb);
    }


    // ---------------------
    // DESERIALIZATION
    // ---------------------
    public TreeNode deserialize(String data) {
        String[] arr = data.split(",");
        int[] idx = {0};
        return buildTree(arr, idx);
    }

    private TreeNode buildTree(String[] arr, int[] idx) {
        if (arr[idx[0]].equals("null")) {
            idx[0]++;
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(arr[idx[0]]));
        idx[0]++;

        node.left = buildTree(arr, idx);
        node.right = buildTree(arr, idx);

        return node;
    }
}
