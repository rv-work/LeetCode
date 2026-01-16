class Solution {
    int res(String s, int i, int j) {

        if (i > j) return 0;
        if (i == j) return 1;

        if (s.charAt(i) == s.charAt(j))
            return 2 + res(s, i + 1, j - 1);

        return Math.max(
            res(s, i + 1, j),
            res(s, i, j - 1)
        );
    }

    public int longestPalindromeSubseq(String s) {
        return res(s, 0, s.length() - 1);
    }
}






class Solution {
    int res(String s, int i, int j , int[][] dp ) {

        if (i > j) return 0;
        if (i == j) return 1;

        if(dp[i][j] != -1) return dp[i][j];

        if (s.charAt(i) == s.charAt(j))
            return dp[i][j] =  2 + res(s, i + 1, j - 1 , dp);

        return dp[i][j] =  Math.max(
            res(s, i + 1, j , dp),
            res(s, i, j - 1 , dp)
        );
    }

    public int longestPalindromeSubseq(String s) {
        int  n = s.length();
        int[][] dp  = new int[n][n];
        for(int arr [] : dp) Arrays.fill(arr , -1);
        return res(s, 0, s.length() - 1 , dp);
    }
}
