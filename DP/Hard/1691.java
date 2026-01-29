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

        // ---- STEP 1: sort each cuboid internally ----IMPORTANT...
        // and we can kyunki ..... ye diya hai ......You can rearrange any cuboid's dimensions by rotating it to put it on another cuboid.
        for (int[] box : cuboids) Arrays.sort(box);

        // ---- STEP 2: sort cuboids by (l, w, h) ----
        Arrays.sort(cuboids, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            if (a[1] != b[1]) return a[1] - b[1];
            return a[2] - b[2];
        }); // ye order (0 then 1 then 2) matter krta hai kyunki upar hmne sort kiya hai already ..... isiliye

        // dp[idx][last+1]
        int[][] dp = new int[n][n + 1];
        for (int[] row : dp) Arrays.fill(row, -1);

        return res(0, -1, cuboids, n , dp);
    }
}














class Solution {
    public int maxHeight(int[][] cuboids) {
        int n = cuboids.length;

        // STEP 1: Sort each cuboid internally
        for (int[] box : cuboids) Arrays.sort(box);

        // STEP 2: Sort cuboids by (l, w, h)
        Arrays.sort(cuboids, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            if (a[1] != b[1]) return a[1] - b[1];
            return a[2] - b[2];
        });

        // dp[idx][last+1]
        int[][] dp = new int[n + 1][n + 1];

        // BASE CASE:
        // When idx == n --> height = 0 for ANY last
        for (int last = -1; last < n; last++) {
            dp[n][last + 1] = 0;
        }

        // FILL TABLE bottom-up
        for (int idx = n - 1; idx >= 0; idx--) {
            for (int last = idx - 1; last >= -1; last--) {

                // ----------- NOT TAKE -----------
                int notTake = dp[idx + 1][last + 1];

                // ----------- TAKE -----------
                int take = 0;
                if (last == -1 ||
                        (cuboids[idx][0] >= cuboids[last][0] &&
                         cuboids[idx][1] >= cuboids[last][1] &&
                         cuboids[idx][2] >= cuboids[last][2])) {

                    take = cuboids[idx][2] + dp[idx + 1][idx + 1];
                }

                // best
                dp[idx][last + 1] = Math.max(take, notTake);
            }
        }

        // answer = dp[0][-1+1] = dp[0][0]
        return dp[0][0];
    }
}








class Solution {
    public int maxHeight(int[][] cuboids) {
        int n = cuboids.length;

        // STEP 1: sort each cuboid internally
        for (int[] box : cuboids) Arrays.sort(box);

        // STEP 2: sort all cuboids
        Arrays.sort(cuboids, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            if (a[1] != b[1]) return a[1] - b[1];
            return a[2] - b[2];
        });

        // next[last+1] = dp[idx+1][last+1]
        int[] next = new int[n + 1];
        int[] curr = new int[n + 1];

        // BASE CASE:
        // dp[n][*] = 0 already handled because next[] initially 0

        // idx from n-1 → 0
        for (int idx = n - 1; idx >= 0; idx--) {

            // last from idx-1 → -1
            for (int last = idx - 1; last >= -1; last--) {

                // -------- NOT TAKE --------
                int notTake = next[last + 1];

                // -------- TAKE --------
                int take = 0;
                if (last == -1 ||
                        (cuboids[idx][0] >= cuboids[last][0] &&
                         cuboids[idx][1] >= cuboids[last][1] &&
                         cuboids[idx][2] >= cuboids[last][2])) {

                    take = cuboids[idx][2] + next[idx + 1];
                }

                curr[last + 1] = Math.max(take, notTake);
            }

            // move curr → next for next iteration
            next = curr.clone();
        }

        // dp[0][-1+1] = dp[0][0]
        return next[0];
    }
}
