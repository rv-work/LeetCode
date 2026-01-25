

// class Solution {

//     int[] dr = { -1, 0, 1, 0 };
//     int[] dc = { 0, 1, 0, -1 };

//     boolean dfs(int[][] g, boolean[][] vis, int r, int c) {
//         int n = g.length;
//         int m = g[0].length;

//         vis[r][c] = true;
//         boolean safe = true;

//         if (r == 0 || r == n - 1 || c == 0 || c == m - 1)
//             safe = false;

//         for (int k = 0; k < 4; k++) {
//             int nr = r + dr[k];
//             int nc = c + dc[k];

//             // Boundary check for valid access
//             if (nr < 0 || nr >= n || nc < 0 || nc >= m)
//                 continue;

//             if (g[nr][nc] == 0 && !vis[nr][nc]) {
//                 safe &=  dfs(g, vis, nr, nc);
//             }
//         }

//         return safe;

//     }

//     public int closedIsland(int[][] g) {
//         int n = g.length;
//         int m = g[0].length;

//         boolean[][] vis = new boolean[n][m];

//         int cnt = 0;

//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < m; j++) {

//                 if (g[i][j] == 0 && !vis[i][j] && dfs(g, vis, i, j)) {
//                     cnt++;
//                 }
//             }
//         }

//         return cnt;
//     }
// }





// class Solution {

//     public int closedIsland(int[][] g) {
//         int n = g.length;
//         int m = g[0].length;

//         boolean[][] vis = new boolean[n][m];
//         int[] dr = {-1, 0, 1, 0};
//         int[] dc = {0, 1, 0, -1};

//         int cnt = 0;

//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < m; j++) {

//                 if (g[i][j] == 0 && !vis[i][j]) {

//                     Queue<int[]> q = new LinkedList<>();
//                     q.add(new int[] {i, j});
//                     vis[i][j] = true;

//                     boolean safe = true;

//                     while (!q.isEmpty()) {
//                         int[] cell = q.poll();
//                         int r = cell[0];
//                         int c = cell[1];

//                          if (r == 0 || r == n-1 || c == 0 || c == m-1)
//                                 safe = false;


//                         for (int k = 0; k < 4; k++) {
//                             int nr = r + dr[k];
//                             int nc = c + dc[k];

//                             if (nr < 0 || nr >= n || nc < 0 || nc >= m)
//                                 continue;

//                             if (g[nr][nc] == 0 && !vis[nr][nc]) {
//                                 vis[nr][nc] = true;
//                                 q.add(new int[]{nr, nc});
//                             }
//                         }
//                     }

//                     if (safe) cnt++;
//                 }
//             }
//         }

//         return cnt;
//     }
// }

// we can do this better ......hme ye dekhne hai ki kaun se land boundry se touch nhi hote... to iski jagah hm ye dekh skte hain ki kaun se lands boundry se touch hote hain,.... traverse from boundry's 0.



class Solution {

    public int closedIsland(int[][] g) {
        int n = g.length;
        int m = g[0].length;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // Step 1: Remove boundary-connected land (0 → 1)
        for (int i = 0; i < n; i++) {
            if (g[i][0] == 0) bfs(i, 0, g, dr, dc);
            if (g[i][m - 1] == 0) bfs(i, m - 1, g, dr, dc);
        }

        for (int j = 0; j < m; j++) {
            if (g[0][j] == 0) bfs(0, j, g, dr, dc);
            if (g[n - 1][j] == 0) bfs(n - 1, j, g, dr, dc);
        }

        // Step 2: Count remaining 0’s → they form closed islands
        int cnt = 0;

        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (g[i][j] == 0) {
                    cnt++;
                    bfs(i, j, g, dr, dc); // remove this island
                }
            }
        }

        return cnt;
    }

    // BFS that removes a connected component (convert 0 → 1)
    void bfs(int r, int c, int[][] g, int[] dr, int[] dc) {
        int n = g.length;
        int m = g[0].length;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        g[r][c] = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            for (int k = 0; k < 4; k++) {
                int nx = x + dr[k];
                int ny = y + dc[k];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m &&
                    g[nx][ny] == 0) {

                    g[nx][ny] = 1;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }
}
