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












class Solution {

    int res(int i, int j,
            int[][] g,
            int[][] dp,
            boolean[] rowDone,
            int[] min1Val,
            int[] min2Val,
            int[] min1Idx) {

        int n = g.length;

        if (i == n - 1)
            return g[i][j];

        // ensure next row is computed
        computeRow(i + 1, g, dp, rowDone, min1Val, min2Val, min1Idx);

        if (j != min1Idx[i + 1])
            return g[i][j] + min1Val[i + 1];
        else
            return g[i][j] + min2Val[i + 1];
    }

    void computeRow(int i,
                    int[][] g,
                    int[][] dp,
                    boolean[] rowDone,
                    int[] min1Val,
                    int[] min2Val,
                    int[] min1Idx) {

        if (rowDone[i]) return;

        int n = g.length;
        min1Val[i] = Integer.MAX_VALUE;
        min2Val[i] = Integer.MAX_VALUE;
        min1Idx[i] = -1;

        for (int j = 0; j < n; j++) {
            if (dp[i][j] == Integer.MAX_VALUE)
                dp[i][j] = res(i, j, g, dp, rowDone, min1Val, min2Val, min1Idx);

            int val = dp[i][j];
            if (val < min1Val[i]) {
                min2Val[i] = min1Val[i];
                min1Val[i] = val;
                min1Idx[i] = j;
            } else if (val < min2Val[i]) {
                min2Val[i] = val;
            }
        }

        rowDone[i] = true;
    }

    public int minFallingPathSum(int[][] g) {
        int n = g.length;

        int[][] dp = new int[n][n];
        for (int[] row : dp)
            Arrays.fill(row, Integer.MAX_VALUE);

        boolean[] rowDone = new boolean[n];
        int[] min1Val = new int[n];
        int[] min2Val = new int[n];
        int[] min1Idx = new int[n];

        // base row
        for (int j = 0; j < n; j++)
            dp[n - 1][j] = g[n - 1][j];

        computeRow(n - 1, g, dp, rowDone, min1Val, min2Val, min1Idx);
        computeRow(0, g, dp, rowDone, min1Val, min2Val, min1Idx);

        return min1Val[0];
    }
}
