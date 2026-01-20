class Solution {
    public int minimumTime(int n, int[][] relations, int[] time) {

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        int[] indeg = new int[n];

        for (int[] r : relations) {
            int u = r[0] - 1;
            int v = r[1] - 1;
            adj.get(u).add(v);
            indeg[v]++;
        }

        Queue<Integer> q = new LinkedList<>();
        int[] dp = new int[n];

        // Initialize
        for (int i = 0; i < n; i++) {
            dp[i] = time[i];
            if (indeg[i] == 0) q.offer(i);
        }

        while (!q.isEmpty()) {
            int u = q.poll();

            for (int v : adj.get(u)) {
                dp[v] = Math.max(dp[v], dp[u] + time[v]);
                indeg[v]--;
                if (indeg[v] == 0) q.offer(v);
            }
        }

        int ans = 0;
        for (int x : dp) ans = Math.max(ans, x);
        return ans;
    }
}
