// class Solution {
//     public TreeNode deleteNode(TreeNode root, int key) {
//         TreeNode parent = null, curr = root;

//         // 1. Search the key
//         while (curr != null && curr.val != key) {
//             parent = curr;
//             if (key < curr.val) curr = curr.left;
//             else curr = curr.right;
//         }

//         if (curr == null) return root; // not found

//         // 2. Replace curr with merge of left & right
//         TreeNode merged = merge(curr.left, curr.right);

//         // 3. Attach to parent
//         if (parent == null) {
//             return merged;
//         } else if (parent.left == curr) {
//             parent.left = merged;
//         } else {
//             parent.right = merged;
//         }

//         return root;
//     }

//     private TreeNode merge(TreeNode left, TreeNode right) {
//         if (left == null) return right;
//         TreeNode temp = left;
//         while (temp.right != null) temp = temp.right;
//         temp.right = right;
//         return left;
//     }
// }




//idea is to have parent and looking fo rkey in the childrens jha mile just update parent left or right 

// but might be root hi delete krna pd jaye so isiliye ... hme ek merged call krenge vha se jo node aayga vhi return kr krenge ...else parent me attach.. simple

// merge me ya to left ke max me right part or right min me left part....


// other ways of doing without parent.... rec+...





// class Solution {
//     public TreeNode deleteNode(TreeNode root, int key) {
//         if (root == null) return null;

//         if (key < root.val) {
//             root.left = deleteNode(root.left, key);
//         } else if (key > root.val) {
//             root.right = deleteNode(root.right, key);
//         } else {
//             // node found
//             if (root.left == null) return root.right;
//             if (root.right == null) return root.left;

//             // find predecessor (max in left subtree)
//             TreeNode pred = root.left;
//             while (pred.right != null) pred = pred.right;

//             root.val = pred.val;
//             root.left = deleteNode(root.left, pred.val);
//         }

//         return root;
//     }
// }

///another ......................



class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } 
        else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } 
        else {
            // Node found
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            // Both children exist → get inorder successor
            TreeNode succ = root.right;
            while (succ.left != null) succ = succ.left;

            root.val = succ.val;   // replace value
            root.right = deleteNode(root.right, succ.val); // delete successor
        }

        return root;
    }
}

