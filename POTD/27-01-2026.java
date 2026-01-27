class Solution {
    public int minCost(int n, int[][] edges) {
        List<List<int[]>> adj = new ArrayList<>();
        List<List<int[]>> adjIncoming = new ArrayList<>();
        for(int i = 0; i<n; i++){
            adj.add(new ArrayList<>());
            adjIncoming.add(new ArrayList<>());
        }


        for(int [] e  : edges){
            int u = e[0];
            int v = e[1];
            int w = e[2];
            adj.get(u).add(new int[]{v , w});
            adjIncoming.get(v).add(new int[]{u,w});
        }

        int dist [] = new int[n];
        Arrays.fill(dist , Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a ,b) -> Integer.compare(a[0],b[0])
        );

        int src = 0;
        dist[src] = 0;
        pq.add(new int[]{0,0});

        while(!pq.isEmpty()){
            int p[] = pq.poll();
            int node = p[1];
            int weight = p[0];


            if (weight > dist[node]) continue;// kyunki incoming me ho skta dubara add ho gya ho.. chhota hone ki vajah se aur baad me vo process ho gya hoga ... to iss outgoing vale joki valid nhi hai ye kaam nhi krenge iske liye.... pq se nikalne me cost jyada lgti isiliye just iss point pr skip krna better hai


            // for outgoing......
            for(int nei [] : adj.get(node)){
                int v = nei[0];
                int w = nei[1];
                if(weight + w < dist[v]){
                    dist[v] = weight + w;
                    pq.add(new int[]{dist[v] , v});
                }

            }

            //for incoming.......
            for(int nei [] : adjIncoming.get(node)){
                int v = nei[0];
                int w = nei[1];
                if(weight + 2*w < dist[v]){
                    dist[v] = weight + 2*w;
                    pq.add(new int[]{dist[v] , v});
                }
            }

        }

        return dist[n-1] == Integer.MAX_VALUE ? -1 : dist[n-1];
    }
}