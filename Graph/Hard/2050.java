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






// this will not work ..because .. any course can start as early possible... not level wise
// means if C1(2) ,C2(3) , C3(5).... running .......next C4(3) aur C4 ke prerequisites C1 & C2 hain to ye unke end hote hi chalne lg jayega naki C3 ka wait krega/..

// so total time .... 6.. not 8 ( upper)...

// shi vala niche hai... see////////////////




class Solution {
    public int minimumTime(int n, int[][] relations, int[] time) {

        List<List<Integer>> adj = new ArrayList<>();
        List<List<Integer>> adj2 = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
            adj2.add(new ArrayList<>());
        }

        int[] in = new int[n];

        for (int[] rel : relations) {
            int prev = rel[0] - 1;
            int next = rel[1] - 1;

            adj.get(prev).add(next);
            adj2.get(next).add(prev);

            in[next]++;
        }

        Queue<Integer> q = new LinkedList<>();
        int[] finish = new int[n];

        for (int i = 0; i < n; i++) {
            finish[i] = time[i];
            if (in[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int nei : adj.get(node)) {

                in[nei]--;

                if (in[nei] == 0) {
                    // compute max finish of all prerequisites
                    int max = 0;
                    for (int dep : adj2.get(nei)) {
                        max = Math.max(max, finish[dep]);
                    }
                    finish[nei] += max;

                    q.add(nei);
                }
            }
        }

        int ans = 0;
        for (int f : finish) ans = Math.max(ans, f);

        return ans;
    }
}











class Solution {
    public int minimumTime(int n, int[][] relations, int[] time) {

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        int[] indeg = new int[n];
        for (int[] r : relations) {
            int u = r[0] - 1, v = r[1] - 1;
            adj.get(u).add(v);
            indeg[v]++;
        }

        int[] finish = new int[n];
        int[] best = new int[n];  // max prereq finish

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            finish[i] = time[i];
            if (indeg[i] == 0) q.add(i);
        }

        while (!q.isEmpty()) {
            int u = q.poll();

            for (int v : adj.get(u)) {
                best[v] = Math.max(best[v], finish[u]);
                indeg[v]--;

                if (indeg[v] == 0) {
                    finish[v] += best[v];
                    q.add(v);
                }
            }
        }

        int ans = 0;
        for (int x : finish) ans = Math.max(ans, x);
        return ans;
    }
}


// int the previous version /...

//  int max = 0;
//  for (int dep : adj2.get(nei)) {
//      max = Math.max(max, finish[dep]);
//  }
//  finish[nei] += max;

// hm dekhte the ki jab ye taiuar hai hone ke liye ab dekho iske pre me se kiska max time tha .. usi me iske time ko add krlo//...


// but usko hm phle se hi calculate kr skte hain ki ...
// sbse phle best initilise krlo ki sab 0 hain
// ab jis point or mai khatam ho gya to to mere jo bhi nei hain un pr bta do ki unko start hone ke liye mere khatam hone tak ka time chahiye hoga vo update krdo... now ho skta hai same node 1 se jyada pr depene ho... to vo vha store hone ke baad jab dusra ayega to bolega mai bhi ho gya update kr do to ab agar ye phle vale se jyada le rha tha to ye aayega otherwise vo already isse jyada wait krne hi vala hai to ye affect nhi krega ... thats why here .....

//  best[v] = Math.max(best[v], finish[u]);












class Solution {

    int res( List<List<Integer>> adj ,int node , int[] time ,  int [] finish){
        if(finish[node] != -1) return finish[node];
        
        int maxPre = 0;
        for(int pre : adj.get(node)){
            maxPre = Math.max(maxPre , res(adj , pre , time ,  finish));
        }
        finish[node] = time[node] + maxPre;

        return finish[node];
    }

    
    public int minimumTime(int n, int[][] relations, int[] time) {

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for (int[] r : relations) {
            int u = r[0] - 1, v = r[1] - 1;
            adj.get(v).add(u);
        }

        int[] finish = new int[n];
        Arrays.fill(finish , -1);
        
        int ans = 0;
        for(int i = 0; i<n; i++){
            ans = Math.max(ans , res(adj , i , time , finish)) ; // assume 0 menas 1 node..
        }


        return ans;

    }
}

