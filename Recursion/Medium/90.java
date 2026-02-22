class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums); // important to handle duplicates
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(0, nums, new ArrayList<>(), ans);
        return ans;
    }

    void backtrack(int idx, int[] nums, List<Integer> temp, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(temp));

        for (int i = idx; i < nums.length; i++) {

            // skip duplicates at same level
            if (i > idx && nums[i] == nums[i - 1])
                continue;

            temp.add(nums[i]);
            backtrack(i + 1, nums, temp, ans);
            temp.remove(temp.size() - 1); // backtrack
        }
    }
}