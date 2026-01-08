class Solution {
    public int jump(int[] nums) {
        return solve(0, nums);
    }

    private int solve(int i, int[] nums) {
        if (i >= nums.length - 1) return 0;

        int min = Integer.MAX_VALUE;

        for (int jump = 1; jump <= nums[i]; jump++) {
            int next = i + jump;
            if (next < nums.length) {
                int res = solve(next, nums);
                if (res != Integer.MAX_VALUE)
                    min = Math.min(min, 1 + res);
            }
        }
        return min;
    }
}
