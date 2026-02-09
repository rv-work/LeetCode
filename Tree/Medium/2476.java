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
