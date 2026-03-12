class Solution {
    int[] parent;
    int[] rank;

    int find(int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent[i]);
    }

    boolean union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        
        if (rootI == rootJ) return false;

        if (rank[rootI] < rank[rootJ]) {
            parent[rootI] = rootJ;
        } else if (rank[rootI] > rank[rootJ]) {
            parent[rootJ] = rootI;
        } else {
            parent[rootJ] = rootI;
            rank[rootI]++;
        }
        return true;
    }

    boolean canFormTree(int n, int[][] edges, int k, int mid) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        int edgesCount = 0;
        int upgradesUsed = 0;

        // Pass 1: Mandatory Edges (Inko lena hi padega, koi option nahi)
        for (int[] e : edges) {
            if (e[3] == 1) {
                // Agar mandatory edge hi required 'mid' se choti hai, toh tree invalid hai
                if (e[2] < mid) return false; 
                // Agar mandatory edges hi cycle bana rahi hain, toh bhi invalid hai
                if (!union(e[0], e[1])) return false; 
                edgesCount++;
            }
        }
        if (edgesCount == n - 1) return true;

        // Pass 2: Free Optional Edges (Jo pehle se 'mid' ya usse badi hain)
        // Inko lene mein koi upgrade waste nahi hoga
        for (int[] e : edges) {
            if (e[3] == 0 && e[2] >= mid) {
                if (union(e[0], e[1])) {
                    edgesCount++;
                    if (edgesCount == n - 1) return true;
                }
            }
        }

        // Pass 3: Need Upgrade Optional Edges (Jo 'mid' se choti hain, par double hoke pohoch jayengi)
        for (int[] e : edges) {
            if (e[3] == 0 && e[2] < mid && (long)e[2] * 2 >= mid) {
                if (union(e[0], e[1])) {
                    upgradesUsed++;
                    if (upgradesUsed > k) return false; // Upgrade budget khatam!
                    
                    edgesCount++;
                    if (edgesCount == n - 1) return true;
                }
            }
        }

        // Agar poora loop chalne ke baad bhi n-1 edges nahi judi, toh false
        return edgesCount == n - 1;
    }

    public int maxStability(int n, int[][] edges, int k) {
   
        if (!canFormTree(n, edges, k, 1)) {
            return -1;
        }

        int low = 1;
        int high = 200000; 
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canFormTree(n, edges, k, mid)) {
                ans = mid; 
                low = mid + 1; 
            } else {
                high = mid - 1; 
            }
        }

        return ans;
    }
}