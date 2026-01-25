class Solution {
    
    // Check if indices are out of bounds
    boolean isOut(int i, int j, int n, int m) {
        return i < 0 || j < 0 || i >= n || j >= m;
    }

    boolean check(int[][] g1, int[][] g2, int i, int j, boolean[][] vis) {
        int n = g1.length, m = g1[0].length;

        // Base Case: 
        // 1. Out of bounds
        // 2. Already visited
        // 3. It's water (0) in grid2 (we only care about traversing land)
        if (isOut(i, j, n, m) || vis[i][j] || g2[i][j] == 0) {
            return true;
        }

        // Mark as visited immediately so we don't process it again
        vis[i][j] = true;

        // Initialize result. 
        // If this cell is land in g2 but water in g1, this island is INVALID (false).
        boolean res = true;
        if (g1[i][j] == 0) {
            res = false; 
        }

        // Recursive calls.
        // We use '&' (bitwise AND) because it is NOT short-circuiting.
        // We want to ensure 'check' is called for ALL directions to mark the whole island visited.
        // If we used '&&', a single false would stop traversal, causing the "Early Exit" bug.

//         res &= check(g1, g2, i - 1, j, vis);
//         res &= check(g1, g2, i + 1, j, vis);
//         res &= check(g1, g2, i, j - 1, vis);
//         res &= check(g1, g2, i, j + 1, vis);.

// wrogn hai kyunki ek bhi wrong huaa to res false ... fir aage ke vo jayega hi nhi kyunki phle hi false jisse sare connected parts mark nhi ho payenge ........ so call them all then get result as combined of all of them ......

        boolean up    = check(g1, g2, i - 1, j, vis);
        boolean down  = check(g1, g2, i + 1, j, vis);
        boolean left  = check(g1, g2, i, j - 1, vis);
        boolean right = check(g1, g2, i, j + 1, vis);
        
        // Combine results. The island is valid only if current cell matches AND all neighbors are valid.
        return res & up & down & left & right;
    }

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int n = grid2.length, m = grid2[0].length;
        boolean[][] vis = new boolean[n][m];
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // Start DFS only if it's land and not visited
                if (grid2[i][j] == 1 && !vis[i][j]) {
                    if (check(grid1, grid2, i, j, vis)) {
                        cnt++;
                    }
                }
            }
        }

        return cnt;
    }
}