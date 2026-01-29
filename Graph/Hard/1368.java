class Solution {
    boolean check(int i, int j, int n, int m) {
        return i >= 0 && i < n && j < m && j >= 0;
    }

    public int minCost(int[][] g) {
        int n = g.length;
        int m = g[0].length;

        int[][] cost = new int[n][m];

        for (int[] arr : cost)
            Arrays.fill(arr, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        cost[0][0] = 0;
        pq.add(new int[] { cost[0][0], 0, 0 });

        int row[] = { 0, -1, 0, +1 };
        int col[] = { -1, 0, +1, 0 };

        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int mineCost = node[0];
            int i = node[1];
            int j = node[2];

            for (int k = 0; k < 4; k++) {
                int r = i + row[k];
                int c = j + col[k];

                if (check(r, c, n, m)) {
                    int before = cost[r][c]; 

                    if (g[i][j] == 1 && r == i && c == j + 1) {
                        cost[r][c] = Math.min(cost[r][c], cost[i][j]);
                    } else if (g[i][j] == 2 && r == i && c == j - 1) {
                        cost[r][c] = Math.min(cost[r][c], cost[i][j]);
                    } else if (g[i][j] == 3 && r == i + 1 && c == j) {
                        cost[r][c] = Math.min(cost[r][c], cost[i][j]);
                    } else if (g[i][j] == 4 && r == i - 1 && c == j) {
                        cost[r][c] = Math.min(cost[r][c], cost[i][j]);
                    } else {
                        cost[r][c] = Math.min(cost[r][c], cost[i][j] + 1);
                    }
   


   // add only if newcost is lesser.. otherwise it will lead to Memory limit exceeded.............
                    if(cost[r][c] < before)
                    pq.add(new int[]{cost[r][c] , r, c});
                }
            }
        }

        return cost[n-1][m-1];

    }
}