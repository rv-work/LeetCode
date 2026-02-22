class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();

        int total = 1 << n; // 2^n subsets

        for (int mask = 0; mask < total; mask++) {
            List<Integer> temp = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                // check if i-th bit in mask is set
                if ((mask & (1 << i)) != 0) {
                    temp.add(nums[i]);
                }
            }

            ans.add(temp);
        }

        return ans;
    }
}








class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();

        solve(0, nums, temp, ans);
        return ans;
    }

    void solve(int idx, int[] nums, List<Integer> temp, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(temp));

        for (int i = idx; i < nums.length; i++) {
            temp.add(nums[i]);            
            solve(i + 1, nums, temp, ans); 
            temp.remove(temp.size() - 1);
        }
    }
}