/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {

  int idx = 0;

  TreeNode build(int[] preorder, int[] inorder ,  int l , int r ,Map<Integer , Integer> map){
        if(l > r) return null;

        TreeNode root = new TreeNode(preorder[idx+++]);
        int inorderRootIdx = map.get(root.val);
       
        root.left = build(preorder , inorder , l , inorderRootIdx-1 , map);
        root.right = build(preorder , inorder , inorderRootIdx+1 , r , map);

        return root;
    }

  public TreeNode buildTree(int[] preorder, int[] inorder) {
    int n = inorder.length;

    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < n; i++) {
      map.put(inorder[i], i);
    }

    return build(preorder, inorder, 0, n - 1, map);
  }
}

// hme global idx hi lena pdega kyunki

// ❌ Problem #1: idx local hai

// idx har recursive call me copy ho jaata hai.
// Iska matlab:

// Left subtree ke baad idx badhta hi nahi

// Right subtree galat element se start hota hai

// Ek simple example se samjho
// Input:
// preorder = [1,2,3]
// inorder = [2,1,3]

// Tumhara flow:

// idx = 0 → root = 1

// left call → idx+1 = 1 → root = 2 ✅

// right call → idx+1 = 1 → root = 2 ❌ (yaha 3 hona chahiye)

// 👉 Same preorder element dobara use ho raha hai

// ❌ Problem #2: Right subtree ka start pata hi nahi

// Right subtree ka root tab aata hai jab left subtree complete ho jaaye
// Lekin tum:

// idx + 1

// bhej rahe ho bina jaane ki:

// left subtree me kitne nodes consume hue

// 🔑 Correct logic kya hoti hai?

// Preorder ka index:

// globally move karta hai

// har node banate waqt 1 step aage badhta hai

// Isliye ya to:

// preIdx global rakho ✅

// ya wrapper class / array use karo

// option 2 .........use pair taki pta chale ki kitne use huye

class Solution {

  static class Pair {
    TreeNode node;
    int idx;

    Pair(TreeNode n, int i) {
      node = n;
      idx = i;
    }
  }

  Pair build(int[] preorder, int l, int r, int idx, Map<Integer, Integer> map) {
    if (l > r)
      return new Pair(null, idx);

    TreeNode root = new TreeNode(preorder[idx]);
    int mid = map.get(root.val);

    Pair left = build(preorder, l, mid - 1, idx + 1, map);
    Pair right = build(preorder, mid + 1, r, left.idx, map);

    root.left = left.node;
    root.right = right.node;

    return new Pair(root, right.idx);
  }

  public TreeNode buildTree(int[] preorder, int[] inorder) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < inorder.length; i++)
      map.put(inorder[i], i);

    return build(preorder, 0, inorder.length - 1, 0, map).node;
  }
}
