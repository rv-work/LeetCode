
class Solution {
    
    int res(int i, int j, String s, int[][] dp) {
        // Base case: Agar bache hi nahi, to 0 turns
        if (i > j) return 0;
        
        // Memoization check
        if (dp[i][j] != -1) return dp[i][j];
        
        // Worst Case: Hum s[i] ko akele print karte hain, aur baaki string ko aage bhej dete hain
        int minTurns = 1 + res(i + 1, j, s, dp);
        
        // Smart Case: i ke aage check karo ki kya koi aur character s[i] jaisa hai?
        for (int k = i + 1; k <= j; k++) {
            if (s.charAt(k) == s.charAt(i)) {
                // Agar s[k] aur s[i] same hain, to hum unhe ek sath print kar sakte hain!
                // To hum string ko 'k' par tod denge:
                // 1. i se k-1 tak ka hissa (jisme s[i] aur s[k] cover ho jayenge)
                // 2. k+1 se j tak ka bacha hua hissa
                int turns = res(i, k - 1, s, dp) + res(k + 1, j, s, dp);
                
                minTurns = Math.min(minTurns, turns);
            }
        }
        
        return dp[i][j] = minTurns;
    }
    
    public int strangePrinter(String s) {
        if (s == null || s.length() == 0) return 0;
        
        // Tumhara Hack: "Jab tak same hain unhe 1 me hi liya jaye"
        // String compression (e.g., "aaabbb" -> "ab")
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                sb.append(s.charAt(i));
            }
        }
        String compressedStr = sb.toString();
        
        int n = compressedStr.length();
        int[][] dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        
        return res(0, n - 1, compressedStr, dp);
    }
}