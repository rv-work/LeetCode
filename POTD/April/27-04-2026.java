// class Solution {

//     boolean dfs(int[][] grid, int r, int c, boolean[][] visited) {
//         int m = grid.length, n = grid[0].length;
        
//         if (r == m - 1 && c == n - 1) return true;
        
//         visited[r][c] = true;
//         int type = grid[r][c];

//         // 1. Check RIGHT
//         // Kya current pipe mujhe RIGHT jaane degi?
//         if (type == 1 || type == 4 || type == 6) {
//             if (c + 1 < n && !visited[r][c + 1]) {
//                 int nextType = grid[r][c + 1];
//                 // Kya agli pipe mujhe LEFT se aane degi?
//                 if (nextType == 1 || nextType == 3 || nextType == 5) {
//                     if (dfs(grid, r, c + 1, visited)) return true;
//                 }
//             }
//         }

//         // 2. Check LEFT
//         // Kya current pipe mujhe LEFT jaane degi?
//         if (type == 1 || type == 3 || type == 5) {
//             if (c - 1 >= 0 && !visited[r][c - 1]) {
//                 int nextType = grid[r][c - 1];
//                 // Kya agli pipe mujhe RIGHT se aane degi?
//                 if (nextType == 1 || nextType == 4 || nextType == 6) {
//                     if (dfs(grid, r, c - 1, visited)) return true;
//                 }
//             }
//         }

//         // 3. Check DOWN
//         // Kya current pipe mujhe DOWN jaane degi?
//         if (type == 2 || type == 3 || type == 4) {
//             if (r + 1 < m && !visited[r + 1][c]) {
//                 int nextType = grid[r + 1][c];
//                 // Kya agli pipe mujhe UP se aane degi?
//                 if (nextType == 2 || nextType == 5 || nextType == 6) {
//                     if (dfs(grid, r + 1, c, visited)) return true;
//                 }
//             }
//         }

//         // 4. Check UP
//         // Kya current pipe mujhe UP jaane degi?
//         if (type == 2 || type == 5 || type == 6) {
//             if (r - 1 >= 0 && !visited[r - 1][c]) {
//                 int nextType = grid[r - 1][c];
//                 // Kya agli pipe mujhe DOWN se aane degi?
//                 if (nextType == 2 || nextType == 3 || nextType == 4) {
//                     if (dfs(grid, r - 1, c, visited)) return true;
//                 }
//             }
//         }

//         return false;
//     }


//     public boolean hasValidPath(int[][] grid) {
//         int m = grid.length, n = grid[0].length;
//         boolean[][] visited = new boolean[m][n];
//         return dfs(grid, 0, 0, visited);
//     }
// }
















// class Solution {
//     public boolean hasValidPath(int[][] grid) {
//         int m = grid.length, n = grid[0].length;
//         boolean[][] visited = new boolean[m][n];
        
//         Queue<int[]> q = new LinkedList<>();
        
//         q.offer(new int[]{0, 0});
//         visited[0][0] = true;

//         while (!q.isEmpty()) {
//             int[] curr = q.poll();
//             int r = curr[0];
//             int c = curr[1];

//             // Base case: Aakhiri block pe pahuch gaye
//             if (r == m - 1 && c == n - 1) return true;

//             int type = grid[r][c];

//             // 1. Check RIGHT
//             if (type == 1 || type == 4 || type == 6) {
//                 if (c + 1 < n && !visited[r][c + 1]) {
//                     int nextType = grid[r][c + 1];
//                     if (nextType == 1 || nextType == 3 || nextType == 5) {
//                         visited[r][c + 1] = true;
//                         q.offer(new int[]{r, c + 1});
//                     }
//                 }
//             }

//             // 2. Check LEFT
//             if (type == 1 || type == 3 || type == 5) {
//                 if (c - 1 >= 0 && !visited[r][c - 1]) {
//                     int nextType = grid[r][c - 1];
//                     if (nextType == 1 || nextType == 4 || nextType == 6) {
//                         visited[r][c - 1] = true;
//                         q.offer(new int[]{r, c - 1});
//                     }
//                 }
//             }

//             // 3. Check DOWN
//             if (type == 2 || type == 3 || type == 4) {
//                 if (r + 1 < m && !visited[r + 1][c]) {
//                     int nextType = grid[r + 1][c];
//                     if (nextType == 2 || nextType == 5 || nextType == 6) {
//                         visited[r + 1][c] = true;
//                         q.offer(new int[]{r + 1, c});
//                     }
//                 }
//             }

//             // 4. Check UP
//             if (type == 2 || type == 5 || type == 6) {
//                 if (r - 1 >= 0 && !visited[r - 1][c]) {
//                     int nextType = grid[r - 1][c];

//                     if (nextType == 2 || nextType == 3 || nextType == 4) {
//                         visited[r - 1][c] = true;
//                         q.offer(new int[]{r - 1, c});
//                     }
//                 }
//             }
//         }

//         return false;
//     }
// }














// class Solution {
//     int[] parent;

//     int find(int i) {
//         if (parent[i] == i) return i;
//         return parent[i] = find(parent[i]); // Compress the path
//     }

//     void union(int i, int j) {
//         int rootI = find(i);
//         int rootJ = find(j);
//         if (rootI != rootJ) {
//             parent[rootI] = rootJ; // Connect the two groups
//         }
//     }

//     public boolean hasValidPath(int[][] grid) {
//         int m = grid.length;
//         int n = grid[0].length;
        
//         //DSU setup. Har cell ka ek ID hoga: (row * n + col)
//         parent = new int[m * n];
//         for (int i = 0; i < m * n; i++) {
//             parent[i] = i; 
//         }

//         // Traverse and Connect
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 int type = grid[i][j];
//                 int currentId = i * n + j;

//                 // 1. Check RIGHT connection
//                 // Current pipe right jaane dena chahiye (1, 4, 6)
//                 if (j + 1 < n && (type == 1 || type == 4 || type == 6)) {
//                     int nextType = grid[i][j + 1];
//                     // Next pipe left se aane dena chahiye (1, 3, 5)
//                     if (nextType == 1 || nextType == 3 || nextType == 5) {
//                         union(currentId, currentId + 1);
//                     }
//                 }

//                 // 2. Check DOWN connection
//                 // Current pipe down jaane dena chahiye (2, 3, 4)
//                 if (i + 1 < m && (type == 2 || type == 3 || type == 4)) {
//                     int nextType = grid[i + 1][j];
//                     // Next pipe up se aane dena chahiye (2, 5, 6)
//                     if (nextType == 2 || nextType == 5 || nextType == 6) {
//                         union(currentId, currentId + n);
//                     }
//                 }
//             }
//         }

//         // Step 3: Check if Start and End belong to the same connected group!
//         int startNode = 0;
//         int endNode = (m * n) - 1;
        
//         return find(startNode) == find(endNode);
//     }

// }















// class Solution {
//     // 0: Up, 1: Down, 2: Left, 3: Right
//     int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
//     int[][] allowed = {
//         {},             // 0: Dummy
//         {2, 3},         // 1: Left, Right
//         {0, 1},         // 2: Up, Down
//         {2, 1},         // 3: Left, Down
//         {3, 1},         // 4: Right, Down
//         {2, 0},         // 5: Left, Up
//         {3, 0}          // 6: Right, Up
//     };


//      boolean dfs(int[][] grid, int r, int c, boolean[][] visited) {
//         int m = grid.length, n = grid[0].length;
        
//         if (r == m - 1 && c == n - 1) return true;
        
//         visited[r][c] = true;
//         int currType = grid[r][c];

//         // Current pipe ke allowed directions me jao

//         for (int dir : allowed[currType]) {
//             int nr = r + dirs[dir][0];
//             int nc = c + dirs[dir][1];

//             // Boundary aur visited check
//             if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc]) {
//                 int nextType = grid[nr][nc];
                
//                 // Kya agla pipe humein aane dega? (Opposite direction check)
//                 if (isValidConnection(nextType, dir ^ 1)) {
//                     if (dfs(grid, nr, nc, visited)) {
//                         return true;
//                     }
//                 }
//             }
//         }
//         return false;
//     }

//     boolean isValidConnection(int pipeType, int incomingDir) {
//         for (int d : allowed[pipeType]) {
//             if (d == incomingDir) return true;
//         }
//         return false;
//     }

//     public boolean hasValidPath(int[][] grid) {
//         int m = grid.length, n = grid[0].length;
//         boolean[][] visited = new boolean[m][n];
//         return dfs(grid, 0, 0, visited);
//     }
// }















// class Solution {
//     int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 0:U, 1:D, 2:L, 3:R
    
//     int[][] allowed = {
//         {}, {2, 3}, {0, 1}, {2, 1}, {3, 1}, {2, 0}, {3, 0}
//     };


//     boolean isValidConnection(int pipeType, int incomingDir) {
//         for (int d : allowed[pipeType]) {
//             if (d == incomingDir) return true;
//         }
//         return false;
//     }

//     public boolean hasValidPath(int[][] grid) {
//         int m = grid.length, n = grid[0].length;
//         boolean[][] visited = new boolean[m][n];
        
//         Queue<int[]> q = new LinkedList<>();
//         q.offer(new int[]{0, 0});
//         visited[0][0] = true;

//         while (!q.isEmpty()) {
//             int[] curr = q.poll();
//             int r = curr[0], c = curr[1];
            
//             if (r == m - 1 && c == n - 1) return true;

//             int currType = grid[r][c];

//             for (int dir : allowed[currType]) {
//                 int nr = r + dirs[dir][0];
//                 int nc = c + dirs[dir][1];

//                 if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc]) {
//                     int nextType = grid[nr][nc];
                    
//                     // Incoming direction (dir ^ 1) check karo
//                     if (isValidConnection(nextType, dir ^ 1)) {
//                         visited[nr][nc] = true;
//                         q.offer(new int[]{nr, nc});
//                     }
//                 }
//             }
//         }
//         return false;
//     }
// }




















class Solution {
    int[] parent;
    
    // 0: Up, 1: Down, 2: Left, 3: Right
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int[][] allowed = {
        {}, {2, 3}, {0, 1}, {2, 1}, {3, 1}, {2, 0}, {3, 0}
    };

    boolean isValidConnection(int type, int incomingDir) {
        for (int d : allowed[type]) {
            if (d == incomingDir) return true;
        }
        return false;
    }

    int find(int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent[i]);
    }

    void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        if (rootI != rootJ) parent[rootI] = rootJ;
    }

    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        parent = new int[m * n];
        
        for (int i = 0; i < m * n; i++) parent[i] = i;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int type = grid[i][j];
                
                // Current pipe ke allowed directions check karo
                for (int dir : allowed[type]) {
                    // Sirf RIGHT (3) aur DOWN (1) check karo taaki duplicate check na ho
                    if (dir == 1 || dir == 3) {
                        int ni = i + dirs[dir][0];
                        int nj = j + dirs[dir][1];

                        // Agar boundary ke andar hai
                        if (ni < m && nj < n) {
                            int nextType = grid[ni][nj];
                            
                            // Kya agla pipe opposite direction (dir ^ 1) se accept kar raha hai?
                            if (isValidConnection(nextType, dir ^ 1)) {
                                union(i * n + j, ni * n + nj); // Connect kardo
                            }
                        }
                    }
                }
            }
        }

        // Kya Start aur End ek hi gang (set) mein hain?
        return find(0) == find(m * n - 1);
    }

    
}
