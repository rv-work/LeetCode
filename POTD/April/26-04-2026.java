

class Solution {
    int[] parent;

    private int find(int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent[i]);
    }
    
    private boolean union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        
        if (rootI == rootJ) return true; 
        
        parent[rootI] = rootJ;
        return false;
    }
    
    public boolean containsCycle(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        parent = new int[m * n];
        
        for (int i = 0; i < m * n; i++) {
            parent[i] = i;
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                
                if (i + 1 < m && grid[i][j] == grid[i + 1][j]) {
                    int node1 = i * n + j;
                    int node2 = (i + 1) * n + j;
                    if (union(node1, node2)) return true;
                }
                
                if (j + 1 < n && grid[i][j] == grid[i][j + 1]) {
                    int node1 = i * n + j;
                    int node2 = i * n + j + 1;
                    if (union(node1, node2)) return true;
                }
            }
        }
        return false;
    }
}