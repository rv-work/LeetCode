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











class Solution {
    public int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        // len is substring length
        for (int len = 2; len <= n; len++) {       
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;

                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][n - 1];
    }
}
