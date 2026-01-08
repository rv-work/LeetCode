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






class Solution {
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return solve(0, nums, dp);
    }

    private int solve(int i, int[] nums, int[] dp) {
        if (i >= nums.length - 1) return 0;

        if (dp[i] != -1) return dp[i];

        int min = Integer.MAX_VALUE;

        for (int jump = 1; jump <= nums[i]; jump++) {
            int next = i + jump;
            if (next < nums.length) {
                int res = solve(next, nums, dp);
                if (res != Integer.MAX_VALUE)
                    min = Math.min(min, 1 + res);
            }
        }
        return dp[i] = min;
    }
}









class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            for (int jump = 1; jump <= nums[i] && i + jump < n; jump++) {
                dp[i + jump] = Math.min(dp[i + jump], dp[i] + 1);
            }
        }
        return dp[n - 1];
    }
}
