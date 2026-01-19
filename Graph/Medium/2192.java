class Solution {
    public List<List<Integer>> getAncestors(int n, int[][] edges) {

        List<List<Integer>> adj = new ArrayList<>();
        List<Set<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
            ans.add(new TreeSet<>()); // TreeSet keeps sorted order
        }

        int[] in = new int[n];

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            adj.get(u).add(v);
            in[v]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (in[i] == 0) q.add(i);
        }

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int nei : adj.get(node)) {
                ans.get(nei).add(node);
                ans.get(nei).addAll(ans.get(node));

                in[nei]--;
                if (in[nei] == 0) q.add(nei);
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            res.add(new ArrayList<>(ans.get(i)));
        }

        return res;
    }
}
