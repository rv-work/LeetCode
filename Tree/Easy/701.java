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
//     public TreeNode insertIntoBST(TreeNode root, int val) {
//         if(root == null) return new TreeNode(val);

//         if(val > root.val){
//              root.right = insertIntoBST(root.right , val); 
//         } else {
//             root.left = insertIntoBST(root.left , val);
//         }

// // assign krna jaruri hai varna update nhi hoga subtree.....its like jake add kro aur yha update krdo 


//         return root;
//     }
// }


// ek aur tareeka hai ki hm nya node ko root bnaye....
// to ek nya node bna do ..acording to confitions...fir root ki val aur uski swap krdo ..

// leets go..... but waiyt this is wrong not only swap.... ye hmesha shi nhi hoga...

// eg..



// Plaintext
//     10
//    /
//   9
// Now, you want to insert 8 as the new Root.

// 8 < 10, so you make 8 the root.

// You push 10 to the Right of 8 (since 10 > 8).

// But 9 is still attached to 10's left side!

// Result:

// Plaintext
//     8
//      \
//       10
//      /
//     9
// This is BROKEN.

// Look at the root 8.

// Its right child is 10 (Correct).

// 10 has a left child 9.

// The problem: 9 is now in the Right Subtree of 8. But 9 > 8 is correct? Yes.

// Wait, let's look at a Left-side failure.

// Better Counter-Example: Tree: Root 10, Right Child 20. Insert 15.

// 15 > 10. Make 15 the Root.

// Push 10 to the Left of 15.

// 20 is still attached to 10.

// Result:

// Plaintext
//       15
//      /
//     10
//      \
//       20  <-- ERROR!
// 20 is now in the Left Subtree of 15.

// But 20 > 15. This violates the BST Property.



// so if we want this the only way is to rotate as we know



// class Solution {
//     public TreeNode insertIntoBST(TreeNode root, int val) {
//         if (root == null) {
//             return new TreeNode(val);
//         }

//         if (val < root.val) {
//             // 1. Insert into left subtree
//             root.left = insertIntoBST(root.left, val);
//             // 2. Rotate Right to bring the new node up
//             return rotateRight(root);
//         } else {
//             // 1. Insert into right subtree
//             root.right = insertIntoBST(root.right, val);
//             // 2. Rotate Left to bring the new node up
//             return rotateLeft(root);
//         }
//     }

//     // Helper: Right Rotation
//     // Lifts the left child up to be the root
//     private TreeNode rotateRight(TreeNode root) {
//         TreeNode newRoot = root.left;
//         root.left = newRoot.right;
//         newRoot.right = root;
//         return newRoot;
//     }

//     // Helper: Left Rotation
//     // Lifts the right child up to be the root
//     private TreeNode rotateLeft(TreeNode root) {
//         TreeNode newRoot = root.right;
//         root.right = newRoot.left;
//         newRoot.left = root;
//         return newRoot;
//     }
// }


// iterative........


class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // If tree is empty, return the new node as root
        if (root == null) return new TreeNode(val);
        
        TreeNode current = root;
        
        while (true) {
            if (val > current.val) {
                // Go Right
                if (current.right == null) {
                    current.right = new TreeNode(val); // Found the spot
                    break;
                }
                current = current.right;
            } else {
                // Go Left
                if (current.left == null) {
                    current.left = new TreeNode(val); // Found the spot
                    break;
                }
                current = current.left;
            }
        }
        
        return root;
    }
}

