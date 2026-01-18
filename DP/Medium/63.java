class Solution {
    boolean check(int i , int j , int m , int n ,int[][] obstacleGrid){
        return i < m && i >= 0 && j < n && j >= 0 && obstacleGrid[i][j] == 0;
    }

    int rec(int i , int j ,int [][] obstacleGrid ,  int [][] dp ){
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        if(i == m-1 && j == n-1) return 1;

        if(dp[i][j] != -1)  return dp[i][j];
        
        int down = 0;
        int right = 0;
        if(check(i+1 , j , m , n , obstacleGrid)) down = rec(i+1 , j , obstacleGrid , dp);
        if(check(i , j+1 , m , n , obstacleGrid)) right = rec(i, j+1 , obstacleGrid , dp);

        return dp[i][j] =  down + right;

    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int [][] dp = new int[m][n];
        for(int arr[] : dp) Arrays.fill(arr , -1);
        if(obstacleGrid[0][0] == 1 ||  obstacleGrid[m-1][n-1] == 1) return 0;
        return rec(0,0,obstacleGrid , dp);
    }
}













class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dp = new int[m+1][n+1];

        if (obstacleGrid[m - 1][n - 1] == 1) return 0;
        dp[m - 1][n - 1] = 1;

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }

                if (i == m - 1 && j == n - 1) continue;

                int down =  dp[i + 1][j] ;
                int right = dp[i][j + 1] ;

                dp[i][j] = down + right;
            }
        }

        return dp[0][0];
    }
}
