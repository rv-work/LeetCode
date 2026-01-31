class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int [][] mat = new int[n][n];
        for(int row [] : mat) Arrays.fill(row , Integer.MAX_VALUE);
        for(int i = 0; i<n; i++){
            mat[i][i] = 0;
        }

        for(int e [] :edges ){
            int u = e[0];
            int v = e[1];
            int w = e[2];
            mat[u][v] = Math.min(mat[u][v] , w);
            mat[v][u] = Math.min(mat[v][u] , w);
        }


        for(int k = 0; k<n; k++){
            for(int i = 0; i<n; i++){
                if(mat[i][k] == Integer.MAX_VALUE) continue;
                for(int j = 0; j<n; j++){
                     if(mat[k][j] == Integer.MAX_VALUE) continue;
                     mat[i][j] = Math.min(mat[i][j] , mat[i][k]+mat[k][j]);
                }
            }
        }

        int cities[] = new int[n];


        for(int i = 0; i<n; i++){
            int cnt = 0;
            for(int j = 0; j<n; j++){
                if(mat[i][j] <= distanceThreshold) cnt++;
            }
            cities[i] = cnt;
        }

       int min = Integer.MAX_VALUE;
       int minI = -1;
       for(int i = 0; i<n; i++){
         if(cities[i] <= min){
            min = cities[i];
            minI = i;
         }
       }

       return minI;

    }
}










class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {

        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            graph[e[1]].add(new int[]{e[0], e[2]});
        }

        int bestCity = -1;
        int bestCount = Integer.MAX_VALUE;

        for (int start = 0; start < n; start++) {
            int count = dijkstra(start, graph, n, distanceThreshold);

            if (count <= bestCount) {
                bestCount = count;
                bestCity = start;   
            }
        }

        return bestCity;
    }

    private int dijkstra(int src, List<int[]>[] graph, int n, int limit) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        pq.add(new int[]{src, 0});
        int count = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0], d = cur[1];

            if (d > dist[u]) continue;
            if (d > limit) continue; // early stop

            count++;

            for (int[] nei : graph[u]) {
                int v = nei[0], w = nei[1];
                if (d + w < dist[v] && d + w <= limit) {
                    dist[v] = d + w;
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }

        return count;
    }
}
