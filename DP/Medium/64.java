class Solution {
    boolean check(int i, int j, int m, int n) {
        return i < m && i >= 0 && j < n && j >= 0;
    }

    int rec(int i, int j, int[][] grid, int[][] dp) {
        int m = grid.length;
        int n = grid[0].length;

        if (i == m - 1 && j == n - 1)
            return grid[i][j];

        if (dp[i][j] != -1)
            return dp[i][j];

        int down = Integer.MAX_VALUE;
        int right = Integer.MAX_VALUE;
        if (check(i + 1, j, m, n))
            down = rec(i + 1, j, grid, dp);
        if (check(i, j + 1, m, n))
            right = rec(i, j + 1, grid, dp);

        return dp[i][j] = grid[i][j] + Math.min(down , right);
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int arr[] : dp)
            Arrays.fill(arr, -1);
        return rec(0, 0, grid, dp);
    }
}