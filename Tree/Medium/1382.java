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

    void inorder(TreeNode root , List<Integer> list) {
        if (root == null) return;
        inorder(root.left ,list);
        list.add(root.val);
        inorder(root.right , list);
    }

    TreeNode build(int l , List<Integer> list ,int r){
        if(l>r) return null;
        int mid = (l+r)/2;
        TreeNode node = new TreeNode(list.get(mid));
        node.left = build(l, list, mid - 1);
        node.right = build(mid+1, list, r);

        return node;
    }

    public TreeNode balanceBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root , list);
        return build(0, list, list.size() - 1);
    }
}