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




// class Solution {
//     TreeNode node1 = null;
//     TreeNode node2 = null;
//     void inorder(TreeNode root){
//         if(root == null) return;

//         inorder(root.left);

//         // ye kaam nhi krega kyunki ye hmesha left node hi dega but hme left nhi vo voilated chahiye ...
//         if(root.left != null && root.left.val > root.val){
//             if(node1 == null){
//                 node1 = root.left;
//             }
//             node2 = root;
          
//         }


//         inorder(root.right);
//     }
//     public void recoverTree(TreeNode root) {
//         inorder(root);
//         int temp = node1.val;
//         node1.val = node2.val;
//         node2.val = temp;
//     }
// }








// Original BST:

//     3
//    / \
//   1   4
//      /
//     2


// Inorder = 1 3 2 4 ❌ (3 and 2 swapped)

// Notice:

// 3 and 2 are wrong

// But 2 is NOT left child of 3

// So your condition never detects it

// Because you're only checking:

// root.left.val > root.val


// But the violation is between:

// prev node in inorder > current node


// NOT parent-child.

// ✅ Correct Way (Key Insight)

// In BST:

// Inorder traversal must be sorted.

// So we must compare:

// prev.val > current.val


// NOT:

// root.left.val > root.val




class Solution {
    TreeNode node1 = null;
    TreeNode node2 = null;
    TreeNode prev = null; /// just a pointer to keep track of... ( so that we go left to right in inorder arr)

    void inorder(TreeNode root){
        if(root == null) return;

        inorder(root.left);

        if(prev != null && prev.val > root.val){
            if(node1 == null){
                node1 = prev;   // first violation
            }
            node2 = root; // this will keep updating whenever we will get ... and last will be out required
        }

        prev = root; // moving pointer from previous to current ,,,

        inorder(root.right);
    }

    public void recoverTree(TreeNode root) {
        inorder(root);

        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
    }
}
