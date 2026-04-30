

class Solution {
    int m, n;
    int[][] grid;
    int[][][] dp;

    int rec(int r, int c, int remK) {
        if (r >= m || c >= n) return -1;

        int cost = (grid[r][c] == 0) ? 0 : 1;
        int score = grid[r][c];

        if (remK < cost) return -1;

        if (r == m - 1 && c == n - 1) return score;

        if (dp[r][c][remK] != -2) {
            return dp[r][c][remK];
        }

        int right = rec(r, c + 1, remK - cost);
        int down  = rec(r + 1, c, remK - cost);

        int maxNext = Math.max(right, down);

        if (maxNext == -1) {
            return -1; 
        }

        return dp[r][c][remK] = score + maxNext;
    }

    public int maxPathScore(int[][] grid, int k) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.grid = grid;
        
        dp = new int[m][n][k + 1];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -2);
            }
        }

        int ans = rec(0, 0, k);
        
        return ans < 0 ? -1 : ans;
    }
}