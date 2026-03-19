class Solution {
    public int numberOfSubmatrices(char[][] g) {

        int n = g.length;
        int m = g[0].length;
        int dp[][][] = new int[n][m][2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int x = 0;
                int y = 0;
                if (g[i][j] == 'X')
                    x = 1;
                if (g[i][j] == 'Y')
                    y = 1;
                if (i - 1 >= 0) {
                    x += dp[i - 1][j][0];
                    y += dp[i-1][j][1];
                }
                if (j - 1 >= 0) {
                    x += dp[i ][j-1][0];
                    y += dp[i][j - 1][1];
                }
                if (i - 1 >= 0 && j - 1 >= 0) {
                    x -= dp[i - 1][j-1][0];
                    y -= dp[i-1][j - 1][1];
                }

                dp[i][j][0] = x;
                dp[i][j][1] = y;
            }
        }

        int cnt = 0;

        for (int mat[][] : dp) {
            for (int[] row : mat) {
                int x = row[0];
                int y = row[1];
                if (x != 0 && x == y)
                    cnt++;
            }
        }

        return cnt;
    }
}