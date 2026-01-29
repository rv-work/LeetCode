class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {

        long[][] mat = new long[26][26];
        for (long[] arr : mat) Arrays.fill(arr, Long.MAX_VALUE);
        for (int i = 0; i < 26; i++) mat[i][i] = 0; // self... VERY IMPORTANT

        int n = original.length;

        for (int i = 0; i < n; i++) {
            int u = original[i] - 'a';
            int v = changed[i] - 'a';
            int w = cost[i];
            // mat[u][v] = (long) w;...wrong.. may be... original = ['a', 'a'], changed = ['b', 'b'], cost = [10, 2]
            // so we need minimum .. 
            mat[u][v] = Math.min(mat[u][v], (long) w);
        }

        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (mat[i][k] != Long.MAX_VALUE && mat[k][j] != Long.MAX_VALUE) {
                        mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
                    }
                }
            }
        }

        int len = source.length();

        long minCost = 0;
        for (int i = 0; i < len; i++) {
            int u = source.charAt(i) - 'a';
            int v = target.charAt(i) - 'a';
            if (u != v) {
                if (mat[u][v] == Long.MAX_VALUE)
                    return -1;
                minCost += mat[u][v];
            }
        }

        return minCost;

    }
}