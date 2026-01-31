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