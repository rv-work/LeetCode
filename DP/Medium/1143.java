class Solution {
    int res(int i , int j , String t1, String t2,int dp[][]){
        int n1 = t1.length();
        int n2 = t2.length(); 

        if(i >= n1 || j >= n2) return 0;

        if(dp[i][j] != -1) return dp[i][j];

        int match = Integer.MIN_VALUE;
        if(t1.charAt(i) == t2.charAt(j)){
            match = 1 + res(i+1 , j+1 , t1 ,t2 ,dp);
        }

        int moveI = res(i+1 , j , t1 ,t2 ,dp);
        int moveJ = res(i , j+1 , t1 ,t2 ,dp);

        return dp[i][j] = Math.max(match , Math.max(moveI , moveJ));
    }
    public int longestCommonSubsequence(String text1, String text2) {
        int n1 = text1.length();
        int n2 = text2.length();
        int dp[][] = new int[n1][n2];
        for(int arr [] : dp) Arrays.fill(arr ,-1);
        return res(0,0,text1,text2,dp);
    }
}






class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int n1 = text1.length();
        int n2 = text2.length();
        int dp[][] = new int[n1 + 1][n2 + 1];

        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {

                int match = Integer.MIN_VALUE;
                if (text1.charAt(i) == text2.charAt(j)) {
                    match = 1 + dp[i + 1][j + 1];
                }
                int moveI = dp[i + 1][j];
                int moveJ = dp[i][j + 1];

                dp[i][j] = Math.max(match, Math.max(moveI, moveJ));
            }
        }

        return dp[0][0];
    }
}