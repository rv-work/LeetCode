class Solution {

    void res(List<List<Integer>> ans, int[] nums, int n, List<Integer> li) {
        if (li.size() == n) {
            ans.add(new ArrayList<>(li)); // store a COPY
            return;
        }

        for (int i = 0; i < n; i++) {
            if (li.contains(nums[i])) continue;

            li.add(nums[i]);
            res(ans, nums, n, li);
            li.remove(li.size() - 1);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        res(ans, nums, nums.length, new ArrayList<>());
        return ans;
    }
}