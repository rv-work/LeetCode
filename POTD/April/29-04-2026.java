

class Solution {
    public long maximumScore(int[][] grid) {
        int n = grid.length;
        long[][] colSum = new long[n][n + 1];

        for (int c = 0; c < n; c++) {
            for (int r = 0; r < n; r++) {
                colSum[c][r + 1] = colSum[c][r] + grid[r][c];
            }
        }

        long[][][] dp = new long[n + 1][n + 1][n + 1];

        for (int col = n - 1; col >= 0; col--) {
            int endNext = (col == n - 1) ? 0 : n;
            
            for (int h_prev = 0; h_prev <= n; h_prev++) {
                for (int h_curr = 0; h_curr <= n; h_curr++) {

                    long maxAns = 0;
                    
                    for (int h_next = 0; h_next <= endNext; h_next++) {
                        
                        long currentColumnScore = 0;
                        int supportHeight = Math.max(h_prev, h_next);
                        
                        if (supportHeight > h_curr) {
                            currentColumnScore = colSum[col][supportHeight] - colSum[col][h_curr];
                        }
                        
                        long totalScore = currentColumnScore + dp[col + 1][h_curr][h_next];
                        maxAns = Math.max(maxAns, totalScore);
                    }
                    
                    dp[col][h_prev][h_curr] = maxAns;
                }
            }
        }

        long maxScore = 0;
        for (int h_curr = 0; h_curr <= n; h_curr++) {
            maxScore = Math.max(maxScore, dp[0][0][h_curr]);
        }

        return maxScore;
    }
}
