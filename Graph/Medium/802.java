class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<List<Integer>> adj = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        int n = graph.length;
        for(int i = 0; i<n; i++){
            adj.add(new ArrayList<>());
        }
        
        int out [] = new int[n];


        for(int i = 0; i<n; i++){
            for(int nei : graph[i]){
                adj.get(nei).add(i);
                out[i]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        for(int i = 0; i<n; i++){
            if(out[i] == 0) q.add(i);
        }

        while(!q.isEmpty()){
            int node = q.poll();
            ans.add(node);

            for(int gotEdgeFrom : adj.get(node)){
                out[gotEdgeFrom]--;
                if(out[gotEdgeFrom] == 0) q.add(gotEdgeFrom);
            }
        }

        Collections.sort(ans);

        return ans;
    }


}