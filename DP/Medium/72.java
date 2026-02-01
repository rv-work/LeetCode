class Solution {
    int res(int i , int j , String w1, String w2, int n1, int n2){
        if(i>=n1) return n2-j;
        if(j>=n2) return n1-i;

        int ans = Integer.MAX_VALUE;
      
        if(w1.charAt(i) == w2.charAt(j)) {
            ans = Math.min(ans , 0 + res(i+1,j+1,w1,w2,n1,n2));
        }else {
            ans = Math.min(ans , 1 + res(i+1,j+1,w1,w2,n1,n2)); // update
            ans = Math.min(ans , 1 + res(i+1,j,w1,w2,n1,n2)); // delete
            ans = Math.min(ans , 1 + res(i,j+1,w1,w2,n1,n2)); // insert
        }


        return ans;
        
    }
    public int minDistance(String w1, String w2) {
        int n1 = w1.length();
        int n2 = w2.length();
        return res(0,0,w1,w2,n1,n2);
    }
}








class Solution {
    int res(int i , int j , String w1, String w2, int n1, int n2,int[][] dp){
        if(i>=n1) return n2-j;
        if(j>=n2) return n1-i;

        if(dp[i][j] != -1) return dp[i][j];

        int ans = Integer.MAX_VALUE;
      
        if(w1.charAt(i) == w2.charAt(j)) {
            ans = Math.min(ans , 0 + res(i+1,j+1,w1,w2,n1,n2,dp));
        }else {
            ans = Math.min(ans , 1 + res(i+1,j+1,w1,w2,n1,n2,dp)); // update
            ans = Math.min(ans , 1 + res(i+1,j,w1,w2,n1,n2,dp)); // delete
            ans = Math.min(ans , 1 + res(i,j+1,w1,w2,n1,n2,dp)); // insert
        }


        return dp[i][j] =  ans;
        
    }
    public int minDistance(String w1, String w2) {
        int n1 = w1.length();
        int n2 = w2.length();
        int[][] dp = new int[n1][n2];
        for(int[] row : dp) Arrays.fill(row ,-1);
        return res(0,0,w1,w2,n1,n2,dp);
    }
}







class Solution {
    public int minDistance(String w1, String w2) {
        int n1 = w1.length();
        int n2 = w2.length();

        int[][] dp = new int[n1 + 1][n2 + 1];

        // Base cases
        for (int i = 0; i <= n1; i++) dp[i][0] = i; // delete all chars
        for (int j = 0; j <= n2; j++) dp[0][j] = j; // insert all chars

        // Fill table
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {

                if (w1.charAt(i - 1) == w2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // no change
                } else {
                    int insert  = dp[i][j - 1] + 1;
                    int delete  = dp[i - 1][j] + 1;
                    int replace = dp[i - 1][j - 1] + 1;

                    dp[i][j] = Math.min(insert, Math.min(delete, replace));
                }
            }
        }

        return dp[n1][n2];
    }
}
