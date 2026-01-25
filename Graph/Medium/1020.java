// class Solution {

//     public int numEnclaves(int[][] grid) {
//         int n = grid.length;
//         int m = grid[0].length;

//         boolean[][] vis = new boolean[n][m];

//         int[] dr = {-1, 1, 0, 0};
//         int[] dc = {0, 0, -1, 1};

//         int total = 0;

//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < m; j++) {

//                 if (grid[i][j] == 1 && !vis[i][j]) {

//                     Queue<int[]> q = new LinkedList<>();
//                     q.add(new int[]{i, j});
//                     vis[i][j] = true;

//                     boolean touchesBoundary = false;
//                     int size = 0;

//                     // BFS for this connected component
//                     while (!q.isEmpty()) {
//                         int[] curr = q.poll();
//                         int r = curr[0], c = curr[1];
//                         size++;

//                         // Check boundary
//                         if (r == 0 || c == 0 || r == n - 1 || c == m - 1)
//                             touchesBoundary = true;

//                         for (int k = 0; k < 4; k++) {
//                             int nr = r + dr[k];
//                             int nc = c + dc[k];

//                             if (nr >= 0 && nr < n && nc >= 0 && nc < m &&
//                                 grid[nr][nc] == 1 && !vis[nr][nc]) {

//                                 vis[nr][nc] = true;
//                                 q.add(new int[]{nr, nc});
//                             }
//                         }
//                     }

//                     // Count only if NOT touching boundary
//                     if (!touchesBoundary) total += size;
//                 }
//             }
//         }

//         return total;
//     }
// }














// class Solution {

//     int n, m;
//     int[][] grid;
//     boolean[][] vis;

//     public int numEnclaves(int[][] grid) {
//         this.grid = grid;
//         n = grid.length;
//         m = grid[0].length;

//         vis = new boolean[n][m];

//         int total = 0;

//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < m; j++) {

//                 if (grid[i][j] == 1 && !vis[i][j]) {

//                     // For each component:
//                     // size = kitne land
//                     // touchesBound = kya yeh boundary ko touch karta hai
//                     Result res = dfs(i, j);

//                     if (!res.touchesBoundary) {
//                         total += res.size;
//                     }
//                 }
//             }
//         }

//         return total;
//     }

//     class Result {
//         int size;
//         boolean touchesBoundary;

//         Result(int size, boolean touchesBoundary) {
//             this.size = size;
//             this.touchesBoundary = touchesBoundary;
//         }
//     }

//     Result dfs(int r, int c) {
//         // Boundary check
//         if (r < 0 || r >= n || c < 0 || c >= m)
//             return new Result(0, true);

//         if (grid[r][c] == 0 || vis[r][c])
//             return new Result(0, false);

//         vis[r][c] = true;

//         int size = 1;
//         boolean touches = false;

//         // If current cell is on boundary → component touches boundary
//         if (r == 0 || r == n - 1 || c == 0 || c == m - 1) {
//             touches = true;
//         }

//         // DFS in 4 directions
//         int[] dr = {-1, 1, 0, 0};
//         int[] dc = {0, 0, -1, 1};

//         for (int k = 0; k < 4; k++) {
//             Result child = dfs(r + dr[k], c + dc[k]);
//             size += child.size;

//             if (child.touchesBoundary)
//                 touches = true;
//         }

//         return new Result(size, touches);
//     }
// }




// Tip.....
// Instead of traversing the entire grid and finding the right cells, only traverse over the edges and find unwanted cells. Mark the unwanted cells. Count the remaining cells.
// This way you won't need to DFS or BFS the entire grid which would bring down the overall complexity




class Solution {

    public int numEnclaves(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        Queue<int[]> q = new LinkedList<>();

        // Step 1: Add all boundary land cells to queue
        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 1) q.add(new int[]{i, 0});
            if (grid[i][m - 1] == 1) q.add(new int[]{i, m - 1});
        }
        for (int j = 0; j < m; j++) {
            if (grid[0][j] == 1) q.add(new int[]{0, j});
            if (grid[n - 1][j] == 1) q.add(new int[]{n - 1, j});
        }

        // Step 2: BFS to remove all land reachable from boundary
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int r = cell[0], c = cell[1];

            if (grid[r][c] == 0) continue; // already visited or water

            grid[r][c] = 0; // mark as water/removed

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] == 1) {
                    q.add(new int[]{nr, nc});
                }
            }
        }

        // Step 3: Count all remaining 1's (land cells not touched)
        int enclaves = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) enclaves++;
            }
        }

        return enclaves;
    }
}


