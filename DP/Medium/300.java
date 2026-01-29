class Solution {

    int res(int idx, int last, int n, int[] nums, int dp[][]) {
        if (idx >= n)
            return 0;
        if (last == -1) {
            return Math.max(1 + res(idx + 1, idx, n, nums, dp), res(idx + 1, last, n, nums, dp));
        }

        if (dp[idx][last] != -1)
            return dp[idx][last];

        int take = 0;
        if (nums[idx] > nums[last]) {
            take = 1 + res(idx + 1, idx, n, nums, dp);
        }
        int notTake = res(idx + 1, last, n, nums, dp);

        return dp[idx][last] = Math.max(notTake, take);
    }

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;

        int dp[][] = new int[n + 1][n + 1];

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int last = n; last >= 0; last--) {
                if (last == n) {
                    dp[idx][last] = Math.max(1 + dp[idx + 1][idx], dp[idx + 1][last]);
                    continue;
                }

                int take = 0;
                if (nums[idx] > nums[last]) {
                    take = 1 + dp[idx+1][idx];
                }
                int notTake = dp[idx+1][last];

                dp[idx][last] = Math.max(notTake, take);
            }
        }


        return dp[0][n]; // because in top down..... 1st call its dp[0][-1]... and here n is playing the role of -1.... so thats why here final thing is .... dp[0][n]... not dp[0][0];
    }
}