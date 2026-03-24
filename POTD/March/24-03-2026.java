class Solution {
    int MOD = 12345;

    public int[][] constructProductMatrix(int[][] g) {
        int n = g.length;
        int m = g[0].length;
        int[][] ans = new int[n][m];

        long suff = 1;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                ans[i][j] = (int) suff;
                suff = (suff * g[i][j]) % MOD;
            }
        }

        long pre = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans[i][j] = (int)((ans[i][j] * pre) % MOD);
                pre = (pre * g[i][j]) % MOD;
            }
        }

        return ans;
    }
}