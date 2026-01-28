class Solution {

    boolean check(int i , int j , int n  ,int[][] g){
        return i>=0 && i<n && j >= 0 && j<n && g[i][j] != 1;
    }


    public int shortestPathBinaryMatrix(int[][] g) {
        int n = g.length;

        if(g[0][0] == 1 || g[n-1][n-1] == 1) return -1;

        int dist[][] = new int[n][n];
        for(int [] arr : dist) Arrays.fill( arr , Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[0] , b[0]));

        dist[0][0] = 1;
        pq.add(new int[]{1,0,0});

        int row [] = {-1,-1,-1,0,0,+1,+1,+1};
        int col [] = {-1,0,+1,-1,+1,-1,0,+1};

        while(!pq.isEmpty()){
            int[] node = pq.poll();
            int w = node[0];
            int i = node[1];
            int j = node[2];

            for(int k = 0; k<8; k++){
                int r = i+row[k];
                int c = j+col[k];
                if(check(r,c ,n , g) && w + 1 < dist[r][c]){
                   dist[r][c] = w + 1;
                   pq.add(new int[]{dist[r][c] , r, c});
                }
            }
        }

        return dist[n-1][n-1] == Integer.MAX_VALUE ? -1 : dist[n-1][n-1] ;
    }
}