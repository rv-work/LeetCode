class Solution {
    int MOD = 1000000007;
    int n;

    long power(long base, long exp) {
        base %= MOD;
        long ans = 1;
        while (exp > 0) {
            if (exp % 2 == 1)
                ans = (base * ans) % MOD;
            base = (base * base) % MOD;
            exp /= 2;
        }
        return ans;
    }

    public int xorAfterQueries(int[] nums, int[][] queries) {
        n = nums.length;
        List<int[]>[] sq = new ArrayList[316];

        for (int i = 0; i < 316; i++) {
            sq[i] = new ArrayList<>();
        }

        for (int[] q : queries) {
            if (q[2] < 316) {
                sq[q[2]].add(q);
            }
        }

        for (int k = 1; k < 316; k++) {
            List<int[]> sk = sq[k];

            long local[] = new long[n];
            Arrays.fill(local, 1L);

            for (int q[] : sk) {
                int l = q[0];
                int steps = (q[1] - q[0]) / k;
                int r = q[0] + (steps * k) + k;
                int v = q[3];

                local[l] = (local[l] * v) % MOD;
                if (r < n) {
                    local[r] = (local[r] * power(v, MOD - 2)) % MOD;
                }
            }

            for (int i = k; i < n; i++) {
                local[i] = (local[i] * local[i - k]) % MOD;
            }

            for (int i = 0; i < n; i++) {
                nums[i] = (int) ((nums[i] * local[i]) % MOD);
            }
        }

        for (int[] q : queries) {
            if (q[2] >= 316) {
                long v = q[3]; 
                for (int i = q[0]; i <= q[1]; i += q[2]) {
                    nums[i] = (int) ((nums[i] * v) % MOD);
                }
            }
        }

        int xor = 0;

        for (int i = 0; i < n; i++) {
            xor ^= nums[i];
        }

        return xor;
    }
}