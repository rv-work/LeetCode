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








class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];


        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // fill DP table
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {

                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(
                        dp[i + 1][j],
                        dp[i][j - 1]
                    );
                }
            }
        }

        return dp[0][n - 1];
    }
}











class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();

        int[] prev = new int[n];
        int[] curr = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            curr[i] = 1; // base case: single character

            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    curr[j] = 2 + prev[j - 1];
                } else {
                    curr[j] = Math.max(curr[j - 1], prev[j]);
                }
            }

            // move row upward
            prev = curr.clone();
        }

        return prev[n - 1];
    }
}
