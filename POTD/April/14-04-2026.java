class Solution {
    long[][] dp;
    
    long rec(int i, int j, List<Integer> r, List<Integer> f) {
        if (i == r.size()) return 0; 
        if (j == f.size()) return (long) 1e15;
        
        if (dp[i][j] != -1) return dp[i][j];
        
        long pick = Math.abs((long) r.get(i) - f.get(j)) + rec(i + 1, j + 1, r, f);
        long skip = rec(i, j + 1, r, f);
        
        return dp[i][j] = Math.min(pick, skip);
    }
    
    public long minimumTotalDistance(List<Integer> r, int[][] f) {
        Collections.sort(r);
        Arrays.sort(f, (a, b) -> Integer.compare(a[0], b[0]));
        
     
        List<Integer> fl = new ArrayList<>();
        for (int[] fact : f) {
            int pos = fact[0];
            int lm = fact[1];
            for (int k = 0; k < lm; k++) {
                fl.add(pos); 
            }
        }
        
        dp = new long[r.size()][fl.size()];
        for (long[] row : dp) Arrays.fill(row, -1);
        
        return rec(0, 0, r, fl);
    }
}