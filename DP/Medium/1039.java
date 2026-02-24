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