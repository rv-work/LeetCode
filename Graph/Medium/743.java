class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {

        List<List<int[]>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for(int[] t : times) {
            int u = t[0]-1;
            int v = t[1]-1;
            int w = t[2];
            adj.get(u).add(new int[]{v, w});
        }

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0]-b[0]);

        dist[k-1] = 0;
        pq.add(new int[]{0, k-1});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int d = cur[0];
            int u = cur[1];

            if (d > dist[u]) continue; 

            for(int[] edge : adj.get(u)) {
                int v = edge[0];
                int w = edge[1];

                if (d + w < dist[v]) {
                    dist[v] = d + w;
                    pq.add(new int[]{dist[v], v});
                }
            }
        }

        int ans = 0;
        for(int x : dist){
            if(x == Integer.MAX_VALUE) return -1;
            ans = Math.max(ans, x);
        }

        return ans;
    }
}
