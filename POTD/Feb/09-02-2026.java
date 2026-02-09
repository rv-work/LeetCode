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

    void inorder(List<Integer> in , TreeNode root){
        if(root == null) return ;
        inorder(in,root.left);
        in.add(root.val);
        inorder(in,root.right);
    }


    TreeNode build(List<Integer> in , int l , int r){
        if(l > r) return null;
        int mid = (l+r)/2;

        TreeNode node = new TreeNode(in.get(mid));
        node.left = build(in,l,mid-1);
        node.right = build(in,mid+1,r);


        return node;
    }


    public TreeNode balanceBST(TreeNode root) {
        List<Integer> in = new ArrayList<>();
        inorder(in,root);
        return build(in,0,in.size()-1);
    }
}