class Solution {

    int[] dfs(
        int node,
        List<List<Integer>> adj,
        List<int[]> dp,
        boolean[] vis,
        boolean[] pathVis,
        String colors,
        boolean[] hasCycle
    ) {
        if (pathVis[node]) {         
            hasCycle[0] = true;
            return new int[26];
        }

        if (vis[node]) {             // memoization
            return dp.get(node);
        }

        pathVis[node] = true;

        int[] cur = dp.get(node);    // dp[node], initially all zeros

        // explore children
        for (int nei : adj.get(node)) {

            int[] child = dfs(nei, adj, dp, vis, pathVis, colors, hasCycle);

            if (hasCycle[0]) return cur;

            // merge child dp into current dp
            for (int i = 0; i < 26; i++) {
                cur[i] = Math.max(cur[i], child[i]);
            }
        }

        // add own color
        cur[colors.charAt(node) - 'a']++;

        vis[node] = true;
        pathVis[node] = false;

        return cur;
    }


    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();

        List<List<Integer>> adj = new ArrayList<>();
        List<int[]> dp = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
            dp.add(new int[26]);      // empty frequency array
        }

        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
        }

        boolean[] vis = new boolean[n];
        boolean[] pathVis = new boolean[n];
        boolean[] hasCycle = new boolean[1];  // since no globals allowed

        int ans = 0;

        for (int i = 0; i < n; i++) {

            int[] res = dfs(i, adj, dp, vis, pathVis, colors, hasCycle);

            if (hasCycle[0]) return -1;

            for (int v : res) ans = Math.max(ans, v);
        }

        return ans;
    }
}







class Solution {

    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();

        List<List<Integer>> adj = new ArrayList<>();
        List<int[]> dp = new ArrayList<>();
        int[] in = new int[n];

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
            dp.add(new int[26]);
        }

        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            in[e[1]]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (in[i] == 0) q.add(i);
        }

        int ans = 0;
        int processed = 0;

        while (!q.isEmpty()) {
            int node = q.poll();
            processed++;

            int[] mine = dp.get(node);
            mine[colors.charAt(node) - 'a']++;

            for (int v : mine) ans = Math.max(ans, v);

            for (int nei : adj.get(node)) {
                int[] fn = dp.get(nei);
                for (int i = 0; i < 26; i++) {
                    fn[i] = Math.max(fn[i], mine[i]);
                }
                in[nei]--;
                if (in[nei] == 0) q.add(nei);
            }
        }

        // cycle check
        if (processed < n) return -1;

        return ans;
    }
}
