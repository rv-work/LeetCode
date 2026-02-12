
class Solution {

    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return solve(n, dp);
    }

    int solve(int n, int[] dp) {
        // Base case
        if (n <= 1) return 1;

        // Already computed
        if (dp[n] != -1) return dp[n];

        int sum = 0;

        for (int i = 1; i <= n; i++) {
            int left = solve(i - 1, dp);
            int right = solve(n - i, dp);
            sum += left * right;
        }

        return dp[n] = sum;
    }
}




