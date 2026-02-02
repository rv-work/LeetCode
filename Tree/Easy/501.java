class Solution {
    HashMap<Integer, Integer> map = new HashMap<>();

    public void func(TreeNode root) {
        if(root==null) return;
        func(root.left);
        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        func(root.right);
    }

    public int[] findMode(TreeNode root) {

        if(root==null) return null;
        func(root);

        int max = 0;
        int size = map.size();
        int arr[][] = new int[size][2];
        int i=0;

        for (int key : map.keySet()) {
            arr[i][0] = key;
            arr[i][1] = map.get(key);
            i++;
        }
        Arrays.sort(arr,(a,b)->{
            return b[1] - a[1];
        });
        i = 0;
        int j=1;

        while(j<size && arr[j][1]==arr[i][1]){
            j++;
        }

        int ans[] = new int[j];

        for(int x=0;x<j;x++){
            ans[x] = arr[x][0];
        }
        return ans;
    }
}








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
    int maxFreq = Integer.MIN_VALUE;
    List<Integer> ans = new ArrayList<>();

    void fill(TreeNode root ,int max,int node){
        if(root == null){
            if(max == maxFreq && !ans.contains(node)) ans.add(node);
            return;
        } 
        if(root.val == node){
            fill(root.left , max+1, node);
            fill(root.right , max+1, node);
        }else {
            if(max == maxFreq &&  !ans.contains(node) ) ans.add(node);
            fill(root.left , 1, root.val);
            fill(root.right , 1, root.val);
        }
    }

    void func(TreeNode root, int max, int node) {
        if (root == null) {
            maxFreq = Math.max(maxFreq, max);
            return;
        }
        if (root.val == node) {
            func(root.left, max + 1, node);
            func(root.right, max + 1, node);
        } else {
            maxFreq = Math.max(maxFreq, max);
            func(root.left, 1, root.val);
            func(root.right, 1, root.val);
        }
    }

    public int[] findMode(TreeNode root) {
        func(root, Integer.MIN_VALUE, Integer.MIN_VALUE);
        fill(root, Integer.MIN_VALUE, Integer.MIN_VALUE);

        int arr[] = new int[ans.size()];
        int i = 0;
        for(int ele : ans){
            arr[i++] = ele;
        }

        return arr;
    }
}


// ye kaam nhi krega kyunki.... ye jaruri nhi ki sare same elements direct relation me ho...
// like ............

    //         6
    //      /     \
    //    2         8
    //  /   \     /   \
    // 0     4   7     9
    //      / \
    //     2   6
