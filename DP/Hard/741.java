class Solution {

    boolean check(int i, int j, int n, int[][] g) {
        return i >= 0 && j >= 0 && i < n && j < n && g[i][j] != -1;
    }

    // For tracking path parent in DOWN journey
    int parentDown[][][];
    // 0 = none, 1 = down, 2 = right

    int goDown(int i, int j, int[][] g) {
        int n = g.length;

        if (i == n - 1 && j == n - 1) {
            parentDown[i][j][0] = 0; 
            return g[i][j];
        }

        int down = -1;
        int right = -1;

        if (check(i + 1, j, n, g))
            down = goDown(i + 1, j, g);
        if (check(i, j + 1, n, g))
            right = goDown(i, j + 1, g);

        if (down < 0 && right < 0)
            return -1;

        if (down >= right) {
            parentDown[i][j][0] = 1; // move DOWN
            return g[i][j] + down;
        } else {
            parentDown[i][j][0] = 2; // move RIGHT
            return g[i][j] + right;
        }
    }

    int goUp(int i, int j, int[][] g) {
        int n = g.length;

        if (i == 0 && j == 0)
            return g[i][j];

        int up = Integer.MIN_VALUE;
        int left = Integer.MIN_VALUE;

        if (check(i - 1, j, n, g))
            up = goUp(i - 1, j, g);
        if (check(i, j - 1, n, g))
            left = goUp(i, j - 1, g);

        int best = Math.max(up, left);

        return g[i][j] + Math.max(0, best);
    }

    List<int[]> buildPathDown(int n) {
        List<int[]> path = new ArrayList<>();
        int i = 0, j = 0;
        path.add(new int[] { i, j });

        while (!(i == n - 1 && j == n - 1)) {
            int move = parentDown[i][j][0];
            if (move == 1)
                i++;
            else
                j++;
            path.add(new int[] { i, j });
        }
        return path;
    }

    public int cherryPickup(int[][] g) {
        int n = g.length;

        parentDown = new int[n][n][1];

        int down = goDown(0, 0, g);
        if (down == -1)
            return 0;

        List<int[]> path1 = buildPathDown(n);

        for (int[] p : path1) {
            g[p[0]][p[1]] = 0;
        }

        int up = goUp(n - 1, n - 1, g);

        return down + up;
    }
}



// this is complete solution...... but this does not work
// optimal is = optimal down + optimal up
// but here down optimal krne me hm up ki optimality khoo dete hai ...

// so not always it gives correct ans .... so hme dono ek sath krne pdeneg 









class Solution {

    int[][][] dp;
    int n;

    boolean check(int i, int j, int n, int[][] g) {
        return i >= 0 && j >= 0 && i < n && j < n && g[i][j] != -1;
    }

    int dfs(int r1, int c1, int r2, int[][] g) {
        int c2 = r1 + c1 - r2;  // derived

        // Out of bounds or thorn 
        if (!check(r1, c1, n, g) || !check(r2, c2, n, g))
            return -10000000; // invalid

        // Reached bottom-right
        if (r1 == n - 1 && c1 == n - 1 ) 
        // r1 + c1  ==  r2 + c2
        // Both persons take exactly same number of steps
        // because they move simultaneously... so 1 phucha to 2 bhi hoga for sure
            return g[r1][c1]; // both must be there now

        if (dp[r1][c1][r2] != Integer.MIN_VALUE)
            return dp[r1][c1][r2];

        // 4 combined moves
        int best = Math.max(
                Math.max(dfs(r1 + 1, c1, r2 + 1, g), dfs(r1 + 1, c1, r2, g)),
                Math.max(dfs(r1, c1 + 1, r2 + 1, g), dfs(r1, c1 + 1, r2, g))
        );

        int cherries = g[r1][c1];
        if (r1 != r2 || c1 != c2)   // different cells
            cherries += g[r2][c2];

        return dp[r1][c1][r2] = cherries + best;
    }

    public int cherryPickup(int[][] g) {
        n = g.length;
        dp = new int[n][n][n];

        for (int[][] a : dp)
            for (int[] b : a)
                Arrays.fill(b, Integer.MIN_VALUE);

        return Math.max(0, dfs(0, 0, 0, g));
    }
}



class Solution {

    public int cherryPickup(int[][] g) {
        int n = g.length;
        int maxK = 2 * (n - 1);

        // dp[k][r1][r2]
        int[][][] dp = new int[maxK + 1][n][n];

        for (int k = 0; k <= maxK; k++)
            for (int i = 0; i < n; i++)
                Arrays.fill(dp[k][i], Integer.MIN_VALUE);

        dp[0][0][0] = g[0][0];    // both start at (0,0)

        for (int k = 1; k <= maxK; k++) {

            for (int r1 = 0; r1 < n; r1++) {
                int c1 = k - r1;
                if (c1 < 0 || c1 >= n) continue;   // out of grid
                if (g[r1][c1] == -1) continue;      // thorn block

                for (int r2 = 0; r2 < n; r2++) {
                    int c2 = k - r2;
                    if (c2 < 0 || c2 >= n) continue;
                    if (g[r2][c2] == -1) continue;

                    int bestPrev = Integer.MIN_VALUE;

                    // 4 possible previous states:

                    // A (r1−1,c1) from up, B (r2−1,c2) from up
                    if (r1 > 0 && r2 > 0)
                        bestPrev = Math.max(bestPrev, dp[k - 1][r1 - 1][r2 - 1]);

                    // A from up, B from left
                    if (r1 > 0 && c2 > 0)
                        bestPrev = Math.max(bestPrev, dp[k - 1][r1 - 1][r2]);

                    // A from left, B from up
                    if (c1 > 0 && r2 > 0)
                        bestPrev = Math.max(bestPrev, dp[k - 1][r1][r2 - 1]);

                    // A from left, B from left
                    if (c1 > 0 && c2 > 0)
                        bestPrev = Math.max(bestPrev, dp[k - 1][r1][r2]);

                    if (bestPrev < 0) continue; // no valid path

                    // cherries collected
                    int add = g[r1][c1];
                    if (r1 != r2 || c1 != c2)
                        add += g[r2][c2];

                    dp[k][r1][r2] = Math.max(dp[k][r1][r2], bestPrev + add);
                }
            }
        }

        return Math.max(0, dp[maxK][n - 1][n - 1]);
    }
}
