class Solution {
    int res(String s, String t, int i, int j,int dp[][]){
        if(j >= t.length()) return 1;
        if(i >= s.length()) return 0;

        if(dp[i][j] != -1) return dp[i][j];

        int pick = 0;
        int notPick = res(s,t,i+1,j,dp);
        if(s.charAt(i) == t.charAt(j)){
            pick = res(s,t,i+1,j+1,dp); // yha bhi chhod skte hain but vo include ho jayega notpick me already..
        }

        return dp[i][j] = pick + notPick;
    }

    public int numDistinct(String s, String t) {
        int n = s.length(); int m = t.length();
        int dp[][] = new int[n][m];
        for(int arr[] : dp) Arrays.fill(arr , -1);
        return res(s,t,0,0,dp);
    }
}













class Solution {
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();

        // dp[i][j] = ways to form t[0..j-1] using s[0..i-1]
        long[][] dp = new long[n + 1][m + 1];

        // empty t can be formed from any prefix of s
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return (int) dp[n][m];
    }
}
