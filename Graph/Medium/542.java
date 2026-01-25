// class Solution {
//     public int[][] updateMatrix(int[][] mat) {
//         int rows = mat.length;
//         int cols = mat[0].length;
//         int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
//         Queue<int[]> queue = new ArrayDeque<>();

//         for (int i = 0; i < rows; i++) {
//             for (int j = 0; j < cols; j++) {
//                 if (mat[i][j] == 0) {
//                     queue.add(new int[] { i, j });
//                 } else {
//                     mat[i][j] = Integer.MAX_VALUE;
//                 }
//             }
//         }

//         while (!queue.isEmpty()) {
//             int[] cell = queue.poll();
//             int row = cell[0];
//             int col = cell[1];

//             for (int[] direction : directions) {
//                 int newRow = row + direction[0];
//                 int newCol = col + direction[1];

//                 if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols
//                         && mat[newRow][newCol] > mat[row][col] + 1) {
//                     mat[newRow][newCol] = mat[row][col] + 1;
//                     queue.add(new int[] { newRow, newCol });
//                 }
//             }
//         }

//         return mat;
//     }
// }








class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        int[][] dist = new int[n][m];
        boolean[][] vis = new boolean[n][m];

        Queue<int[]> q = new LinkedList<>();

        // Step 1: Add ALL 0-cells to queue first
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    q.add(new int[]{i, j});
                    vis[i][j] = true;
                }
            }
        }

        // Directions for BFS
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // Step 2: Multi-source BFS
        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int r = cell[0];
            int c = cell[1];

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m && !vis[nr][nc]) {
                    dist[nr][nc] = dist[r][c] + 1;
                    vis[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                }
            }
        }

        return dist;
    }
}
