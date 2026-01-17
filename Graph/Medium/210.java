class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int [] inDeg = new int[numCourses];
        List<List<Integer>> adj = new ArrayList<>();

        for(int i = 0; i<numCourses; i++){
            adj.add(new ArrayList<>());
        }

        int ans[] = new int[numCourses];

        for(int num [] : prerequisites){
            int a = num[0];
            int b = num[1];
            inDeg[a]++;
            adj.get(b).add(a);
        }

        Queue<Integer> q = new LinkedList<>();


     
        for(int i = 0; i<numCourses; i++){
            if(inDeg[i] == 0) {
               q.add(i);
            }
        }



        int i = 0;

        while(!q.isEmpty()){
            int node = q.poll();
            ans[i++] = node;
            
            for(int nei : adj.get(node)){
                inDeg[nei]--;
                if(inDeg[nei] == 0) q.add(nei);
            }
        }

        if(i < numCourses-1) return new int[0];

        return ans;
    }
}
