
class Solution {
    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {

        int n = original.length;
        Map<String, Integer> map = new HashMap<>();
        int cnt = 0;

        Set<Integer> sizes = new HashSet<>();

        for (String s : original) {
            if (!map.containsKey(s))
                map.put(s, cnt++);
            sizes.add(s.length());
        }
        for (String s : changed) {
            if (!map.containsKey(s))
                map.put(s, cnt++);
            sizes.add(s.length());
        }

        long[][] mat = new long[cnt][cnt];
        for (long[] row : mat)
            Arrays.fill(row, Long.MAX_VALUE);
        for (int i = 0; i < cnt; i++)
            mat[i][i] = 0;

        for (int i = 0; i < n; i++) {
            int u = map.get(original[i]);
            int v = map.get(changed[i]);
            mat[u][v] = Math.min(mat[u][v], cost[i]);
        }

        for (int k = 0; k < cnt; k++) {
            for (int i = 0; i < cnt; i++) {
                if (mat[i][k] == Long.MAX_VALUE)
                    continue;
                for (int j = 0; j < cnt; j++) {
                    if (mat[k][j] != Long.MAX_VALUE) {
                        mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
                    }
                }
            }
        }


// backward..
        // int len = source.length();
        // long[] dp = new long[len + 1];
        // Arrays.fill(dp, Long.MAX_VALUE);
        // dp[len] = 0;

        // for (int i = len - 1; i >= 0; i--) {

        //     if (source.charAt(i) == target.charAt(i)) {
        //         dp[i] = dp[i + 1];
        //     }

        //     for (int size : sizes) {
        //         int end = i + size;
        //         int start = i;
        //         if (end > len){
        //             continue;
        //         }
        //         String subSource = source.substring(start, end);
        //         String subTarget = target.substring(start, end);

        //         if (subSource.equals(subTarget)) {
        //             dp[i] = Math.min(dp[i] , dp[end]);
        //             continue;
        //         }

        //         if (map.containsKey(subSource) && map.containsKey(subTarget)) {
        //             int u = map.get(subSource);
        //             int v = map.get(subTarget);
        //             long val = mat[u][v];
        //             if (dp[end] != Long.MAX_VALUE && val != Long.MAX_VALUE) {
        //                 dp[i] = Math.min(dp[i], val + dp[end]);
        //             }

        //         }
        //     }
        // }




//forward......
        int len = source.length();
        long[] dp = new long[len + 1];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <=len; i++) {

            if (source.charAt(i-1) == target.charAt(i-1)) {
                dp[i] = dp[i - 1];
            }

            for (int size : sizes) {
                int end = i;
                int start = i-size;
                if (start  < 0){
                    continue;
                }
                String subSource = source.substring(start, end);
                String subTarget = target.substring(start, end);

                if (subSource.equals(subTarget)) {
                    dp[i] = Math.min(dp[i] , dp[start]);
                    continue;
                }

                if (map.containsKey(subSource) && map.containsKey(subTarget)) {
                    int u = map.get(subSource);
                    int v = map.get(subTarget);
                    long val = mat[u][v];
                    if (dp[start] != Long.MAX_VALUE && val != Long.MAX_VALUE) {
                        dp[i] = Math.min(dp[i], val + dp[start]);
                    }

                }
            }
        }


        return dp[len] == Long.MAX_VALUE ? -1 : dp[len];
    }
}