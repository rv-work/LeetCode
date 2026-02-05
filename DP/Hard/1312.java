class Solution {
    int res(int i ,int j , String s,int[][]dp){
        if(i>j) return 0;

        if(dp[i][j] != -1) return dp[i][j] ;

        int match = Integer.MAX_VALUE;
        int notMatch = Integer.MAX_VALUE;
        if(s.charAt(i) == s.charAt(j)){
            match = res(i+1,j-1,s,dp);
        }else {
            notMatch = 1 + Math.min(res(i+1,j,s,dp),res(i,j-1,s,dp));
        }


        return dp[i][j]  =  Math.min(match,notMatch);
    }
    public int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int arr[] : dp)Arrays.fill(arr, -1);
        return res(0,n-1,s,dp);
    }
}