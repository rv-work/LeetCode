class Solution {

    public int closedIsland(int[][] g) {
        int n = g.length;
        int m = g[0].length;

        boolean[][] vis = new boolean[n][m];
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        int cnt = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (g[i][j] == 0 && !vis[i][j]) {

                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[] {i, j});
                    vis[i][j] = true;

                    boolean safe = true;

                    while (!q.isEmpty()) {
                        int[] cell = q.poll();
                        int r = cell[0];
                        int c = cell[1];

                         if (r == 0 || r == n-1 || c == 0 || c == m-1)
                                safe = false;


                        for (int k = 0; k < 4; k++) {
                            int nr = r + dr[k];
                            int nc = c + dc[k];

                            if (nr < 0 || nr >= n || nc < 0 || nc >= m)
                                continue;

                            if (g[nr][nc] == 0 && !vis[nr][nc]) {
                                vis[nr][nc] = true;
                                q.add(new int[]{nr, nc});
                            }
                        }
                    }

                    if (safe) cnt++;
                }
            }
        }

        return cnt;
    }
}
