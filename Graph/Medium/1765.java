class Solution {
    public int[][] highestPeak(int[][] isWater) {
        int n = isWater.length;
        int m = isWater[0].length;

        int[][] height = new int[n][m];
        boolean[][] vis = new boolean[n][m];

        Queue<int[]> q = new LinkedList<>();

        // Step 1: Push all water cells (height = 0)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (isWater[i][j] == 1) {
                    q.add(new int[]{i, j});
                    vis[i][j] = true;
                    height[i][j] = 0;
                }
            }
        }

        // Directions for BFS      
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // Step 2: Multi-source BFS
        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int r = cell[0], c = cell[1];

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m && !vis[nr][nc]) {
                    vis[nr][nc] = true;
                    height[nr][nc] = height[r][c] + 1;   // height increases outward
                    q.add(new int[]{nr, nc});
                }
            }
        }

        return height;
    }
}
