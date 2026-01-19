class Solution {

    int res(int i, int j, int[][] g, int[][] dp) {
        int n = g.length;
        if (i == n - 1)
            return g[i][j];

        if (dp[i][j] != -1)
            return dp[i][j];

        int min = Integer.MAX_VALUE;
        for (int col = 0; col < n; col++) {
            if (col == j)
                continue;
            min = Math.min(min, res(i + 1, col, g, dp));
        }

        return dp[i][j] = min + g[i][j];
    }

    public int minFallingPathSum(int[][] g) {
        int n = g.length;

        int[][] dp = new int[n][n];

        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = g[n - 1][j];
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int min = Integer.MAX_VALUE;
                for (int col = 0; col < n; col++) {
                    if (col == j)
                        continue;
                    min = Math.min(min, dp[i+1][col]);
                }
                dp[i][j] = min + g[i][j];
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            ans = Math.min(ans, dp[0][j]);
        }
        return ans;
    }
}