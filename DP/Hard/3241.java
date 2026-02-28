
class Solution {
    void fillDp(int[][] dp, List<List<Integer>> adj, int node, int parent) {
        for (int nei : adj.get(node)) {
            if (nei != parent) {
                fillDp(dp, adj, nei, node);

                int tillNei = (nei % 2 == 0) ? 2 : 1;
                int total = tillNei + dp[nei][0];

                if (total > dp[node][0]) {
                    dp[node][1] = dp[node][0];
                    dp[node][0] = total;
                    dp[node][2] = nei;
                } else if (total > dp[node][1]) {
                    dp[node][1] = total;
                }
            }
        }
    }

    void fillAns(int[][] dp, List<List<Integer>> adj, int node, int parent, int[] ans, int prevVal) {
        ans[node] = Math.max(prevVal, dp[node][0]);

        for (int nei : adj.get(node)) {
            if (nei != parent) {
                int tillParent = (node % 2 == 0) ? 2 : 1;
                int bestFromParent;

                if (nei == dp[node][2]) {
                    bestFromParent = Math.max(prevVal, dp[node][1]);
                } else {
                    bestFromParent = Math.max(prevVal, dp[node][0]);
                }

                int nextParVal = bestFromParent + tillParent;
                fillAns(dp, adj, nei, node, ans, nextParVal);
            }
        }
    }

    public int[] timeTaken(int[][] edges) {
        int n = edges.length + 1;
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        int[][] dp = new int[n][3];
        fillDp(dp, adj, 0, -1);

        int[] ans = new int[n];
        fillAns(dp, adj, 0, -1, ans, 0);

        return ans;
    }
}


// ---------------------------------------------
// GRAND SUMMARY — RE-ROOTING DP (O(N) Solution)
// ---------------------------------------------

// 1) Build Tree (Adjacency List):
//    - Given N-1 edges, create an undirected adjacency list
//    - Required for DFS traversal in both directions.

// 2) DP Structure per Node (dp[node][3]):
//    dp[node][0] = longest path from any child (First Max)
//    dp[node][1] = second longest path (Second Max)
//    dp[node][2] = which child gave First Max (to avoid overlap)

// 3) Bottom-Up DFS (fillDp):
//    - Process children first, then parent (post-order).
//    - For each child:
//        childContribution = (child % 2 == 0 ? 2 : 1)
//        total = dp[child][0] + childContribution
//    - Update First Max, Second Max, and the child responsible.
//    - After this step, dp[0][0] = best downward path from root.

// 4) Top-Down DFS (fillAns) — Rerooting:
//    - ans[node] = max(dp[node][0], prevValFromParent)
//    - For each child:
//        If child gave First Max → use Second Max for parent contribution
//        Otherwise → use First Max
//    - Add parent’s cost (1 or 2) and pass value downwards.
//    - Ensures each child gets the best possible value from ABOVE its parent.

// 5) Final:
//    - ans[] contains the maximum value each node can achieve considering
//      the entire tree (both below it and above it).
//    - Overall complexity: O(N) using two DFS passes.
// ---------------------------------------------