/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
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
//  */
// class Solution {

//     // middle find karo
//     private ListNode findMid(ListNode head) {
//         ListNode prev = null;
//         ListNode slow = head;
//         ListNode fast = head;

//         while (fast != null && fast.next != null) {
//             prev = slow;
//             slow = slow.next;
//             fast = fast.next.next;
//         }

//         // break left half
//         if (prev != null) prev.next = null;

//         return slow;
//     }

//     public TreeNode sortedListToBST(ListNode head) {
//         if (head == null) return null;

//         // find middle
//         ListNode mid = findMid(head);

//         // middle becomes root
//         TreeNode root = new TreeNode(mid.val);

//         // if only one element — no need to split further
//         if (head == mid) return root;

//         // build left and right subtree
//         root.left = sortedListToBST(head);
//         root.right = sortedListToBST(mid.next);

//         return root;
//     }
// }


// with conversion......................


// class Solution {

//     public TreeNode sortedListToBST(ListNode head) {

//         // 1. Convert to array
//         List<Integer> arr = new ArrayList<>();
//         while (head != null) {
//             arr.add(head.val);
//             head = head.next;
//         }

//         // 2. Build BST using array
//         return build(arr, 0, arr.size() - 1);
//     }

//     TreeNode build(List<Integer> arr, int l, int r) {
//         if (l > r) return null;

//         int mid = (l + r) / 2;
//         TreeNode root = new TreeNode(arr.get(mid));

//         root.left = build(arr, l, mid - 1);
//         root.right = build(arr, mid + 1, r);

//         return root;
//     }
// }


//... best Inorder Simulation

// idea ye hai ki head global rkhenge aur hr bar use bnayenge from left to right as inorder .....
// we will call for left .. half krke ... jab vo 1 ho jayega tab bnega node ... fir vo us point pr head ko aage kr dega ru fir vo laut kr aayega apne parent step pr .. yha ke liye head aage move ho chuka hai ( from left building ) ab ise root bna lo aur right call kr do... bache huye me ... vha bhi bnte bnte bn jayega aise me 



// List: [-10 → -3 → 0 → 5 → 9]
// Count = 5
// So first call:

// build(5)
// ⭐ STEP-BY-STEP VISUALISATION
// (With recursion tree + head pointer movement)

// 🎯 CALL 1 → build(5)
// build(5):
//     left = build(5/2 = 2)
// 🎯 CALL 2 → build(2)
// build(2):
//     left = build(1)
// 🎯 CALL 3 → build(1)
// build(1):
//     left = build(0) --> returns null
// Now:

// root = new TreeNode(head.val) = -10
// head moves -> pointing to -3
// root.right = build(0) --> null
// RETURN node(-10)
// 📌 Return to CALL 2

// Left subtree ready:

//    -10
// Head now at:

// head → -3
// 🎯 Back to CALL 2 (build(2))
// Now:

// root = new TreeNode(head.val) = -3
// head moves -> pointing to 0
// Now build right subtree of size 2 - 1 - 1 = 0

// So:

// right = build(0) -> null
// CALL 2 RETURNS:

//     -3
//    /
//  -10
// 📌 Return to CALL 1

// Head now at:

// head → 0
// 🎯 Back to CALL 1 (build(5))
// Left subtree ready:

//     -3
//    /
//  -10
// Now:

// root = new TreeNode(head.val) = 0
// head moves -> pointing to 5
// Now build right subtree of size:

// remaining = 5 - 2 - 1 = 2
// right = build(2)
// 🎯 CALL 4 → build(2)
// Same pattern:

// build(2):
//     left = build(1)
// 🎯 CALL 5 → build(1)
// Left subtree:

// left = build(0) → null
// root = head.val = 5
// head moves → pointing to 9
// right = build(0)
// RETURN node(5)
// 📌 Return to CALL 4

// 🎯 Back to CALL 4
// Now:

// root = new TreeNode(head.val) = 9
// head moves → null
// Right subtree:

// right = build(0)
// CALL 4 RETURNS:

//    9
//   /
//  5
// 🎯 Back to CALL 1
// Now we have:

// Left:

//     -3
//    /
//  -10
// Root:

// 0
// Right:

//    9
//   /
//  5
// 🌳 FINAL TREE
//         0
//       /   \
//     -3     9
//    /      /
//  -10     5
// Balanced!
// Sorted!
// Perfect BST!

// 🧠 SUMMARY OF HEAD MOVEMENT
// Step	Node Created	Head Moves To
// 1	-10	-3
// 2	-3	0
// 3	0	5
// 4	5	9
// 5	9	null
// Exactly in inorder order.



class Solution {

    ListNode head;

    int count(ListNode node){
        int c = 0;
        while(node != null){
            c++;
            node = node.next;
        }
        return c;
    }

    TreeNode buildTree(int n){
      if(n <= 0) return null;

      TreeNode left = buildTree(n/2);

      TreeNode root = new TreeNode(head.val);

      head = head.next;

      root.right = buildTree(n - n/2 - 1);
      root.left = left;

      return root;
    }

    public TreeNode sortedListToBST(ListNode h) {
        this.head = h;
        int n = count(h);
        return buildTree(n);
    }
}
