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











class Solution {

    int LCS(int n1, int n2, String s1, String s2, int dp[][]) {
        
        if (n1 == 0 || n2 == 0)
            return 0;
        
        if(dp[n1-1][n2-1]!=-1) return dp[n1-1][n2-1];

        if (s1.charAt(n1-1) == s2.charAt(n2-1)) {
            dp[n1-1][n2-1]  =  1 + LCS(n1 - 1, n2 - 1, s1, s2, dp);
        } else {
            dp[n1-1][n2-1] = Math.max(LCS(n1 - 1, n2, s1, s2, dp), LCS(n1, n2 - 1, s1, s2, dp));
        }

        return dp[n1-1][n2-1];
    }

    public int minInsertions(String s) {
        int n = s.length();

        StringBuilder sb = new StringBuilder(s);

        String s2 = sb.reverse().toString();

        int dp[][] = new int[n][n];
        for(int []row:dp) Arrays.fill(row,-1);

        return n - LCS(n, n, s, s2, dp);
    }
}
/*

zza
zza
6 - 2*()

mbadm
mdabm

(10 - 2*(3))/2


leetcode
edocteel

16 - 2*()

*/