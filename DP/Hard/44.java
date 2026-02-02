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
