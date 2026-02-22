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