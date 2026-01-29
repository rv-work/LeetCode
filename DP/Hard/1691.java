class Solution {

    int res(int idx, int last, int[][] c, int n, int[][] dp) {
        if (idx == n) return 0;

        if (dp[idx][last + 1] != -1) return dp[idx][last + 1];

        // ---- NOT TAKE ----
        int notTake = res(idx + 1, last, c, n ,dp);

        // ---- TAKE ----
        int take = 0;
        if (last == -1 ||
            (c[idx][0] >= c[last][0] &&
             c[idx][1] >= c[last][1] &&
             c[idx][2] >= c[last][2])) 
        {
            take = c[idx][2] + res(idx + 1, idx, c, n , dp);
        }

        return dp[idx][last + 1] = Math.max(take, notTake);
    }

    public int maxHeight(int[][] cuboids) {
        int n = cuboids.length;

        // ---- STEP 1: sort each cuboid internally ----
        for (int[] box : cuboids) Arrays.sort(box);

        // ---- STEP 2: sort cuboids by (l, w, h) ----
        Arrays.sort(cuboids, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            if (a[1] != b[1]) return a[1] - b[1];
            return a[2] - b[2];
        });

        // dp[idx][last+1]
        int[][] dp = new int[n][n + 1];
        for (int[] row : dp) Arrays.fill(row, -1);

        return res(0, -1, cuboids, n , dp);
    }
}
