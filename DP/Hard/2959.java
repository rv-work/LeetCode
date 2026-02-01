
class Solution {

    public int numberOfSets(int n, int maxDist, int[][] roads) {
        boolean[] open = new boolean[n];
        return dfs(0, n, maxDist, roads, open);
    }

    private int dfs(int idx, int n, int maxDist, int[][] roads, boolean[] open) {
        if (idx == n) {
            return isValid(n, maxDist, roads, open) ? 1 : 0;
        }

        // choose OPEN
        open[idx] = true;
        int take = dfs(idx + 1, n, maxDist, roads, open);

        // choose CLOSED
        open[idx] = false;
        int notTake = dfs(idx + 1, n, maxDist, roads, open);

        return take + notTake;
    }

    private boolean isValid(int n, int maxDist, int[][] roads, boolean[] open) {
        int INF = 1_000_000;
        int[][] dist = new int[n][n];

        // Initialize
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = (i == j ? 0 : INF);
            }
        }

        // Add edges only for open nodes
        for (int[] r : roads) {
            int u = r[0], v = r[1], w = r[2];
            if (open[u] && open[v]) {
                dist[u][v] = Math.min(dist[u][v], w);
                dist[v][u] = Math.min(dist[v][u], w);
            }
        }

        // Floyd-Warshall but only on open nodes
        for (int k = 0; k < n; k++) {
            if (!open[k]) continue;
            for (int i = 0; i < n; i++) {
                if (!open[i]) continue;
                for (int j = 0; j < n; j++) {
                    if (!open[j]) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        // Validate distance condition
        for (int i = 0; i < n; i++) {
            if (!open[i]) continue;
            for (int j = 0; j < n; j++) {
                if (!open[j]) continue;
                if (dist[i][j] > maxDist) return false;
            }
        }

        return true;
    }
}
