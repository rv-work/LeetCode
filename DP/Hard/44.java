class Solution {

    Boolean res(int i, int j, String s, String p, Boolean [][] dp) {
        if (j == p.length()) {
            return i == s.length();
        }

        if (i == s.length()) {
            for (int k = j; k < p.length(); k++) {
                if (p.charAt(k) != '*') return false;
            }
            return true;
        }

        if (dp[i][j] != null) return dp[i][j];

        boolean ans = false;

        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
            ans = res(i + 1, j + 1, s, p, dp);
        }
        else if (p.charAt(j) == '*') {
            ans = res(i, j + 1, s, p, dp) || res(i + 1, j, s, p, dp);
        }

        return dp[i][j] = ans;
    }

    public boolean isMatch(String s, String p) {
        Boolean [][] dp = new Boolean[s.length() + 1][p.length() + 1];
        return res(0, 0, s, p, dp);
    }
}








class Solution {
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();

        boolean[] prev = new boolean[m + 1];
        boolean[] curr = new boolean[m + 1];

        // Empty s and empty p
        prev[0] = true;

        // Fill first row: s = ""
        for (int j = 1; j <= m; j++) {
            if (p.charAt(j - 1) == '*')
                prev[j] = prev[j - 1];
            else
                prev[j] = false;
        }

        for (int i = 1; i <= n; i++) {
            curr[0] = false; // empty pattern can't match non-empty string

            for (int j = 1; j <= m; j++) {
                char pc = p.charAt(j - 1);
                char sc = s.charAt(i - 1);

                if (pc == sc || pc == '?') {
                    curr[j] = prev[j - 1];
                } else if (pc == '*') {
                    curr[j] = prev[j] || curr[j - 1];
                } else {
                    curr[j] = false;
                }
            }

            // shift curr → prev
            boolean[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[m];
    }
}
