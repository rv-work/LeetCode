class Solution {
    int solve(int i , int j , int[] nums , int [][]dp){
        if(j-i< 2){
            return 0;
        }

        if(dp[i][j] != -1) return dp[i][j];

        int min = Integer.MAX_VALUE;
        for(int k = i+1; k<j; k++){
            int cost = nums[i]*nums[k]*nums[j]+ solve(i , k , nums , dp)  + solve(k ,j , nums , dp);
            min  = Math.min(min , cost );
        }



        return dp[i][j] = min;
    }
    public int minScoreTriangulation(int[] values) {
        int n  =  values.length;
        int dp[][] = new int[n][n];
        for(int row [] : dp) Arrays.fill(row , -1);
;        return solve(0,n-1,values , dp);
    }
}








class Solution {
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];

        // gap = length of subpolygon
        for (int gap = 2; gap < n; gap++) {   // gap < 2 has cost = 0
            for (int i = 0; i + gap < n; i++) {
                int j = i + gap;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i + 1; k < j; k++) {
                    int cost = values[i] * values[k] * values[j]
                             + dp[i][k] 
                             + dp[k][j];

                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        return dp[0][n - 1];
    }
}