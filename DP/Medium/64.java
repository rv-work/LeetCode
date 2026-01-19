class Solution {
    boolean check(int i, int j, int m, int n) {
        return i < m && i >= 0 && j < n && j >= 0;
    }

    int rec(int i, int j, int[][] grid, int[][] dp) {
        int m = grid.length;
        int n = grid[0].length;

        if (i == m - 1 && j == n - 1)
            return grid[i][j];

        if (dp[i][j] != -1)
            return dp[i][j];

        int down = Integer.MAX_VALUE;
        int right = Integer.MAX_VALUE;
        if (check(i + 1, j, m, n))
            down = rec(i + 1, j, grid, dp);
        if (check(i, j + 1, m, n))
            right = rec(i, j + 1, grid, dp);

        return dp[i][j] = grid[i][j] + Math.min(down , right);
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int arr[] : dp)
            Arrays.fill(arr, -1);
        return rec(0, 0, grid, dp);
    }
}









class Solution {
    boolean check(int i, int j, int m, int n) {
        return i < m && i >= 0 && j < n && j >= 0;
    }

    int rec(int i, int j, int[][] grid, int[][] dp) {
        int m = grid.length;
        int n = grid[0].length;

        if (i == m - 1 && j == n - 1)
            return grid[i][j];

        if (dp[i][j] != -1)
            return dp[i][j];

        int down = Integer.MAX_VALUE;
        int right = Integer.MAX_VALUE;
        if (check(i + 1, j, m, n))
            down = rec(i + 1, j, grid, dp);
        if (check(i, j + 1, m, n))
            right = rec(i, j + 1, grid, dp);

        return dp[i][j] = grid[i][j] + Math.min(down, right);
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m+1][n+1];
        
        for(int i = 0; i<=m ; i++){
            dp[i][n] = Integer.MAX_VALUE;
        }
        for(int j = 0; j<=n ; j++){
            dp[m][j] = Integer.MAX_VALUE;
        }


        for(int i = m-1; i>= 0; i--){
            for(int j = n-1; j>= 0; j--){

                int down = dp[i+1][j];
                int right = dp[i][j+1];
                int min =  Math.min(down , right);

                dp[i][j] = grid[i][j];
                if(min != Integer.MAX_VALUE) dp[i][j] += min;
                
    
            }
        }

        return dp[0][0];
    }
}







class Solution {
    boolean check(int i, int j, int m, int n) {
        return i < m && i >= 0 && j < n && j >= 0;
    }

    int rec(int i, int j, int[][] grid, int[][] dp) {
        int m = grid.length;
        int n = grid[0].length;

        if (i == m - 1 && j == n - 1)
            return grid[i][j];

        if (dp[i][j] != -1)
            return dp[i][j];

        int down = Integer.MAX_VALUE;
        int right = Integer.MAX_VALUE;
        if (check(i + 1, j, m, n))
            down = rec(i + 1, j, grid, dp);
        if (check(i, j + 1, m, n))
            right = rec(i, j + 1, grid, dp);

        return dp[i][j] = grid[i][j] + Math.min(down, right);
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[]curr = new int[n+1];
        int[]next = new int[n+1];
        
        for(int i = 0; i<=n ; i++){
            next[i] = Integer.MAX_VALUE;
        }
        curr[n] = Integer.MAX_VALUE;

        

        for(int i = m-1; i>= 0; i--){
            for(int j = n-1; j>= 0; j--){

                int down = next[j];
                int right = curr[j+1];
                int min =  Math.min(down , right);

                curr[j] = grid[i][j];
                if(min != Integer.MAX_VALUE) curr[j] += min;
    
            }

            next = curr;
        }

        return curr[0];
    }
}