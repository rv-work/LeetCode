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












class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {

            for (int j = 0; j < n; j++) {

                if (j < i) {
                    dp[i][j] = 0;   // 🔥 INVALID SUBSTRING (forcefully set)
                }
                else if (i == j) {
                    dp[i][j] = 1;   // single char → palindrome
                }
                else if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                }
                else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }

        
            }
        }

        return dp[0][n - 1];
    }
}









class Solution {
    int res(int i , int j , String s,  int [][] dp){
        if(i>j) return 0;
        if(dp[i][j] != -1) return dp[i][j];

        int moveI = Integer.MIN_VALUE;
        int moveJ = Integer.MIN_VALUE;
        int match = Integer.MIN_VALUE;

        if(s.charAt(i) == s.charAt(j)){
            match = 2 + res(i+1,j-1,s,dp);
            if(i == j) match -= 1;
        } else {
            moveI = res(i+1,j,s,dp);
            moveJ = res(i,j-1,s,dp);
        }

        return dp[i][j]= Math.max(match , Math.max(moveI , moveJ));
    }
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int [][] dp = new int[n+1][n+1];
        for(int [] arr : dp) Arrays.fill(arr, Integer.MIN_VALUE);
        
        for(int i = n-1; i>=0; i--){
            for(int j = 0; j<=i; j++){
                
                int moveI = Integer.MIN_VALUE;
                int moveJ = Integer.MIN_VALUE;
                int match = Integer.MIN_VALUE;
        
                if(s.charAt(i) == s.charAt(j)){
                    match = 2 ;
                    if(j > 0 && i<n && dp[i+1][j-1] != Integer.MIN_VALUE)  match += dp[i+1][j-1];
                    if(i == j) match -= 1;
                } else {
                    if(i<n)moveI = dp[i+1][j];
                    if(j>0) moveJ = dp[i][j-1];
                }
                dp[i][j]= Math.max(match , Math.max(moveI , moveJ));
            }
        }

        int max = Integer.MIN_VALUE;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                max = Math.max(max , dp[i][j]);
            }
        }

        return max;
    }
}