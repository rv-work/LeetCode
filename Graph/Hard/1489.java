
class Solution {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];
            adj.get(u).add(new int[]{v, w, i});
            adj.get(v).add(new int[]{u, w, i});
        }

        // 1. Calculate the Standard MST Weight first
        int minMST = getMST(n, adj, -1, -1, edges);

        List<Integer> criticals = new ArrayList<>();
        List<Integer> pseudoCriticals = new ArrayList<>();

        // 2. Iterate through ALL edges (not just the ones in a Set)
        for (int i = 0; i < edges.length; i++) {
            
            // Check Critical: Run MST IGNORING edge 'i'
            int weightWithout = getMST(n, adj, i, -1, edges);
            
            if (weightWithout > minMST) {
                // If weight increases or graph becomes disconnected, it's Critical
                criticals.add(i);
            } else {
                // Check Pseudo-Critical: Run MST FORCING edge 'i' to be included
                int weightWith = getMST(n, adj, -1, i, edges);
                
                if (weightWith == minMST) {
                    // If we forced it and weight is still same, it's Pseudo-Critical
                    pseudoCriticals.add(i);
                }
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        ans.add(criticals);
        ans.add(pseudoCriticals);
        return ans;
    }

    // Helper: Prim's Algorithm
    // blockEdge: index of edge to skip
    // forceEdge: index of edge to start with
    private int getMST(int n, List<List<int[]>> adj, int blockEdge, int forceEdge, int[][] edges) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        boolean[] vis = new boolean[n];
        int mstWeight = 0;
        int nodesCount = 0;

        // Logic to FORCE an edge (Start Prim's from this edge)
        if (forceEdge != -1) {
            int u = edges[forceEdge][0];
            int v = edges[forceEdge][1];
            int w = edges[forceEdge][2];
            
            vis[u] = true;
            vis[v] = true;
            mstWeight += w;
            nodesCount += 2; // We visited 2 nodes
            
            // Add neighbors of BOTH u and v
            for (int[] next : adj.get(u)) if (next[2] != blockEdge) pq.add(next);
            for (int[] next : adj.get(v)) if (next[2] != blockEdge) pq.add(next);
        } else {
            // Standard Prim's Start from node 0
            vis[0] = true;
            nodesCount++;
            for (int[] next : adj.get(0)) {
                if (next[2] != blockEdge) pq.add(next);
            }
        }

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0];
            int w = curr[1];
            int idx = curr[2];

            if (vis[u]) continue; // Skip if visited
            
            vis[u] = true;
            mstWeight += w;
            nodesCount++;

            for (int[] next : adj.get(u)) {
                // Don't add visited nodes OR the blocked edge
                if (!vis[next[0]] && next[2] != blockEdge) {
                    pq.add(next);
                }
            }
        }

        // If we didn't visit all nodes, the graph is disconnected
        return (nodesCount == n) ? mstWeight : Integer.MAX_VALUE;
    }
}