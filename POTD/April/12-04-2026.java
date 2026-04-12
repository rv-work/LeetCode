
class Solution {
    int[][][] dp;
    
    int d(int a, int b) {
        if (a == 26) return 0; 
        return Math.abs(a / 6 - b / 6) + Math.abs(a % 6 - b % 6);
    }
    
    int rec(String s, int i, int f1, int f2) {
        if (i == s.length()) return 0;
        
        if (dp[i][f1][f2] != -1) return dp[i][f1][f2];
        
        int c = s.charAt(i) - 'A';
        
        int op1 = d(f1, c) + rec(s, i + 1, c, f2);
        
        int op2 = d(f2, c) + rec(s, i + 1, f1, c);
        
        return dp[i][f1][f2] = Math.min(op1, op2);
    }
    
    public int minimumDistance(String word) {
        int n = word.length();
        dp = new int[n][27][27];
        
        for (int[][] m2 : dp) {
            for (int[] m1 : m2) {
                Arrays.fill(m1, -1);
            }
        }
                
        return rec(word, 0, 26, 26);
    }
}