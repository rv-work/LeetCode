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
class BSTIterator {

    void inorder(List<TreeNode> in , TreeNode root){
        if(root == null) return ;

        inorder(in , root.left);
        in.add(root);
        inorder(in , root.right);
    }

    
    List<TreeNode> in;
    int i = 0;
    public BSTIterator(TreeNode root) {
        in = new ArrayList<>();
        inorder(in , root);
    
    }
    
    public int next() {
       return in.get(i++).val;
    }
    
    public boolean hasNext() {
        return i<in.size();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */