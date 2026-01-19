class Solution {
    
    
    int res(List<List<Integer>> g , int i , int j , int n , int m  , int dp [][]){
        if(i >= n && j >= m) return 0;

        if(dp[i][j] != -1) return dp[i][j];
        
        int down = Integer.MIN_VALUE; int right = Integer.MIN_VALUE;
        if(i+1 < m) down =  g.get(i+1).get(j) - g.get(i).get(j) + res(g , i+1 , j , n , m , dp);
        if(j+1 < n) right =  g.get(i).get(j+1) - g.get(i).get(j) + res(g , i , j+1 , n , m , dp);

        return dp[i][j] =  Math.max(0 , Math.max(down , right));

    }

    public int maxScore(List<List<Integer>> g) {
        int m = g.size();
        int n = g.get(0).size();

        int dp [][] = new int[m][n];
        for(int [] arr : dp) Arrays.fill(arr , -1);

        
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                max2 = Math.max(max2 , res(g , i , j , n , m , dp));
                if(j+1 < n) max1 = Math.max(max1 , g.get(i).get(j+1) - g.get(i).get(j));
                if(i+1 < m) max1 = Math.max(max1 , g.get(i+1).get(j) - g.get(i).get(j));
            }
        }

        if(max2==0) return max1;

        return max2;

       
    }
}