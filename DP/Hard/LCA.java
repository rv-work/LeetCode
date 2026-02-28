import java.util.*;

public class LCAOptimized {
    
    static int LOG;
    static int[][] up; // up[u][j] stores the 2^j-th ancestor of node u
    static int[] depth;
    static List<List<Integer>> adj;

    // ==========================================
    // METHOD 1: BINARY LIFTING (Active Method)
    // ==========================================
    
    // DFS to find depth and populate the 'up' table
    public static void dfsBinaryLifting(int u, int p, int d) {
        depth[u] = d;
        up[u][0] = p; // 2^0 (1st) ancestor is the direct parent
        
        // Precompute the 2^j-th ancestor
        for (int j = 1; j < LOG; j++) {
            if (up[u][j - 1] != -1) {
                up[u][j] = up[up[u][j - 1]][j - 1];
            } else {
                up[u][j] = -1;
            }
        }
        
        for (int v : adj.get(u)) {
            if (v != p) {
                dfsBinaryLifting(v, u, d + 1);
            }
        }
    }

    public static int getLCABinaryLifting(int u, int v) {
        // Step 1: Ensure u is deeper than v. If not, swap them.
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        
        // Step 2: Bring u and v to the same depth
        int diff = depth[u] - depth[v];
        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) { // If the j-th bit is set
                u = up[u][j];
            }
        }
        
        // If they meet, that node is the LCA
        if (u == v) return u;
        
        // Step 3: Jump together to just below the LCA
        for (int j = LOG - 1; j >= 0; j--) {
            if (up[u][j] != -1 && up[u][j] != up[v][j]) {
                u = up[u][j];
                v = up[v][j];
            }
        }
        
        // The parent of u (or v) is the LCA
        return up[u][0];
    }

    // ==========================================
    // METHOD 2: EULER TOUR + SPARSE TABLE (Commented)
    // ==========================================
    /*
    static int[] euler, firstOccurrence, eulerDepth;
    static int timer = 0;
    static int[][] sparseTable;
    
    // 1. DFS to build Euler Tour array and depths
    public static void dfsEuler(int u, int p, int d) {
        firstOccurrence[u] = timer;
        euler[timer] = u;
        eulerDepth[timer++] = d;
        for (int v : adj.get(u)) {
            if (v != p) {
                dfsEuler(v, u, d + 1);
                euler[timer] = u;
                eulerDepth[timer++] = d;
            }
        }
    }
    
    // 2. Build Sparse Table for Range Minimum Query (RMQ) on eulerDepth array
    public static void buildSparseTable() {
        // Logic to build sparseTable[i][j] representing minimum depth in range
    }
    
    // 3. O(1) Query
    public static int getLCAEuler(int u, int v) {
        int left = firstOccurrence[u];
        int right = firstOccurrence[v];
        if (left > right) { // swap
            int temp = left; left = right; right = temp;
        }
        // Find minimum in eulerDepth from index 'left' to 'right' using Sparse Table
        // Return the corresponding node from 'euler' array
        return -1; // placeholder
    }
    */

    // ==========================================
    // METHOD 3: TARJAN'S OFFLINE LCA (Commented)
    // ==========================================
    /*
    static int[] parent; // DSU parent array
    static boolean[] visited;
    static int[] lcaResult; // Store answers for queries
    
    // DSU Find method
    public static int find(int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent[i]);
    }
    
    public static void dfsTarjan(int u, int p) {
        // Process all children
        for (int v : adj.get(u)) {
            if (v != p) {
                dfsTarjan(v, u);
                // Union child with parent
                parent[v] = u; 
            }
        }
        visited[u] = true;
        
        // Process queries related to node 'u'
        // for each query (u, v):
        //     if (visited[v]) {
        //         lcaResult[queryIndex] = find(v);
        //     }
    }
    */

    public static void main(String[] args) {
        int n = 8; // Number of nodes (0 to 7)
        LOG = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
        
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        
        up = new int[n][LOG];
        depth = new int[n];
        
        // Creating a Sample Tree
        //        0
        //       / \
        //      1   2
        //     / \   \
        //    3   4   5
        //       / \
        //      6   7
        
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {4, 6}, {4, 7}};
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]); // undirected tree
        }
        
        // 1. Preprocessing (Root is 0, Parent of root is -1, Depth is 0)
        dfsBinaryLifting(0, -1, 0);
        
        // 2. Queries
        System.out.println("LCA of 6 and 7 is: " + getLCABinaryLifting(6, 7)); // Output: 4
        System.out.println("LCA of 3 and 7 is: " + getLCABinaryLifting(3, 7)); // Output: 1
        System.out.println("LCA of 6 and 5 is: " + getLCABinaryLifting(6, 5)); // Output: 0
    }
}