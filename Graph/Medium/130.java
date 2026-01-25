class Solution {

    public void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;

        boolean[][] vis = new boolean[n][m];

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (board[i][j] == 'O' && !vis[i][j]) {

                    Queue<int[]> q = new LinkedList<>();
                    List<int[]> comp = new ArrayList<>();

                    boolean safe = true;

                    vis[i][j] = true;
                    q.add(new int[]{i, j});
                    comp.add(new int[]{i, j});

                    while (!q.isEmpty()) {
                        int[] cell = q.poll();
                        int r = cell[0], c = cell[1];

                        // boundary touch → not safe
                        if (r == 0 || r == n - 1 || c == 0 || c == m - 1) {
                            safe = false;
                        }

                        for (int k = 0; k < 4; k++) {
                            int nr = r + dr[k];
                            int nc = c + dc[k];

                            if (nr >= 0 && nr < n && nc >= 0 && nc < m &&
                                board[nr][nc] == 'O' && !vis[nr][nc]) {

                                vis[nr][nc] = true;
                                q.add(new int[]{nr, nc});
                                comp.add(new int[]{nr, nc});
                            }
                        }
                    }

                    // If SAFE → flip them all
                    if (safe) {
                        for (int[] cell : comp) {
                            board[cell[0]][cell[1]] = 'X';
                        }
                    }

                }
            }
        }
        
    }
}






class Solution {

    public void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;

        boolean[][] vis = new boolean[n][m];

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (board[i][j] == 'O' && !vis[i][j]) {

                    // store this component's cells
                    List<int[]> component = new ArrayList<>();
                    boolean[] safe = new boolean[]{true};

                    dfs(i, j, board, vis, component, safe, dr, dc);

                    // If region does NOT touch boundary → flip all O → X
                    if (safe[0]) {
                        for (int[] cell : component) {
                            board[cell[0]][cell[1]] = 'X';
                        }
                    }
                    // else → do nothing (backtrack automatically done by not flipping)
                }
            }
        }
    }

    void dfs(int r, int c, char[][] board, boolean[][] vis,
             List<int[]> component, boolean[] safe, int[] dr, int[] dc) {

        int n = board.length;
        int m = board[0].length;

        vis[r][c] = true;
        component.add(new int[]{r, c});

        // touches boundary → not safe to flip
        if (r == 0 || r == n - 1 || c == 0 || c == m - 1) {
            safe[0] = false;
        }

        for (int k = 0; k < 4; k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];

            if (nr >= 0 && nr < n && nc >= 0 && nc < m &&
                board[nr][nc] == 'O' && !vis[nr][nc]) {

                dfs(nr, nc, board, vis, component, safe, dr, dc);
            }
        }
    }
}
