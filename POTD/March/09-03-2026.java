class Solution {
    int L;
    int MOD = 1000000007;
    int[][][] dp;

    int res(int z, int o, int turn) {
        if (z < 0 || o < 0) return 0;
        if (z == 0 && o == 0) return 1;

        if (dp[z][o][turn] != -1) {
            return dp[z][o][turn];
        }

        long ways = 0;

        if (turn == 0) {
            // Agar 0 ki baari hai, toh 1 se L tak kitne bhi 0 lagao
            for (int k = 1; k <= Math.min(z, L); k++) {
                // 0 lagane ke baad, agli baari 1 ki aayegi (turn = 1)
                ways = (ways + res(z - k, o, 1)) % MOD;
            }
        } else {
            // Agar 1 ki baari hai, toh 1 se L tak kitne bhi 1 lagao
            for (int k = 1; k <= Math.min(o, L); k++) {
                // 1 lagane ke baad, agli baari 0 ki aayegi (turn = 0)
                ways = (ways + res(z, o - k, 0)) % MOD;
            }
        }

        return dp[z][o][turn] = (int) ways;
    }

    public int numberOfStableArrays(int zero, int one, int limit) {
        L = limit;
        
        // DP Array ki 3rd dimension ab sirf size 2 ki hai! (0 or 1)
        dp = new int[zero + 1][one + 1][2];
        
        for (int i = 0; i <= zero; i++) {
            for (int j = 0; j <= one; j++) {
                dp[i][j][0] = -1;
                dp[i][j][1] = -1;
            }
        }

        // Shuruwat 0 ke block se karo + Shuruwat 1 ke block se karo
        return (res(zero, one, 0) + res(zero, one, 1)) % MOD; 
    }
}