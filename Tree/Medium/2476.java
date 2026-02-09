class Solution {
    void inorder(List<Integer> in , TreeNode root){
        if(root == null) return;

        inorder(in, root.left);
        in.add(root.val);
        inorder(in, root.right);
    }

    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> q) {
        List<Integer> in = new ArrayList<>();
        inorder(in, root);

        List<List<Integer>> ans = new ArrayList<>();

        for (int num : q) {

            // lower bound: greatest <= num
            int l = 0, r = in.size() - 1;
            int min = -1;

            while (l <= r) {
                int mid = (l + r) >> 1;
                if (in.get(mid) <= num) {
                    min = in.get(mid);
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }

            // upper bound: smallest >= num
            l = 0; r = in.size() - 1;
            int max = -1;

            while (l <= r) {
                int mid = (l + r) >> 1;
                if (in.get(mid) >= num) {
                    max = in.get(mid);
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }

            ans.add(Arrays.asList(min, max));
        }

        return ans;
    }
}









class Solution {
    void inorder(List<Integer> in, TreeNode root) {
        if (root == null) return;
        inorder(in, root.left);
        in.add(root.val);
        inorder(in, root.right);
    }

    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> q) {
        List<Integer> in = new ArrayList<>();
        inorder(in, root);

        List<List<Integer>> ans = new ArrayList<>();

        for (int x : q) {
            int idx = Collections.binarySearch(in, x);

            if (idx >= 0) { // exact match is availble
                ans.add(Arrays.asList(x, x));
            } else {
                idx = -idx - 1; // insertion point
                int mn = (idx - 1 >= 0) ? in.get(idx - 1) : -1;
                int mx = (idx < in.size()) ? in.get(idx) : -1;
                ans.add(Arrays.asList(mn, mx));
            }
        }
        return ans;
    }
}


// ye int idx = Collections.binarySearch(in, x);..... jo idx deta hai ye insertion point deta hai matlan ki -insertionpoint - 1.... to isi ko balance krne ke liye hm baaad me   idx = -idx - 1; ... aisa krte hain 


//............INSERTION POINT MATLAB .... KAHA PR HONA CHAHYE THA .............


// 🎯 SABSE PEHLE: insertion point kya hota hai?

// Sorted list socho:

// in = [2, 4, 6, 9]


// Agar tum ek number sorted order banaye rakhte hue insert karna chaho,
// to jaha insert hoga → wahi insertion point hota hai.

// Bas itna simple.

// ⭐ CASE-1: x exists
// x = 6
// in = [2, 4, 6, 9]


// 6 list me already hai → index = 2
// So:

// binarySearch returns = 2


// Isme insertion point ka concept use nahi hota.

// ⭐ CASE-2: x is smaller than all elements
// x = 1
// in = [2, 4, 6, 9]


// 1 should be inserted at very start:

// index: 0  1  2  3
// value: 2  4  6  9
//         ↑
// insert at index 0


// 📌 Insertion point = 0

// binarySearch returns…

// -(insertionPoint) - 1
// = -(0) - 1
// = -1


// Now reverse it:

// idx = -(-1) - 1 = 0


// Matlab insertion point sahi aa gaya.

// ⭐ CASE-3: x fits between two elements
// x = 5
// in = [2, 4, 6, 9]


// Sorted order me 5 ko kahaan insert karoge?

// index: 0  1  2  3
// value: 2  4  6  9
//              ↑
// 5 should be inserted at index 2


// 📌 Insertion point = 2

// binarySearch return:

// -(2) - 1 = -3


// Reverse:

// idx = -(-3) - 1 = 2


// Bingo → exact insertion point mil gaya.

// ⭐ CASE-4: x greater than all
// x = 20
// in = [2, 4, 6, 9]


// 20 insert hoga list ke end me:

// index: 0  1  2  3  4
// value: 2  4  6  9
//                     ↑
//                 insert at index 4


// 📌 insertion point = 4
// (binarySearch returns negative)

// -(4) - 1 = -5


// Reverse:

// idx = -(-5) - 1 = 4