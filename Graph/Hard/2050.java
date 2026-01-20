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
