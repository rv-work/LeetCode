class Solution {

    boolean validate(int[][] lcp , char[] ans){
        int n = lcp.length;
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (ans[i] == ans[j]) {
                    int expectedLcp = (i + 1 < n && j + 1 < n) ? lcp[i + 1][j + 1] + 1 : 1;
                    if (lcp[i][j] != expectedLcp) return false;
                } 
                else {
                    if (lcp[i][j] != 0) return false;
                }
            }
        }
        return true;
    }

    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] ans = new char[n];
        ans[0] = 'a';

        for (int i = 1; i < n; i++) {
            boolean deny[] = new boolean[26];
            boolean got = false;
            for (int j = 0; j < i; j++) {
                if (lcp[j][i] > 0) {
                    got = true;
                    ans[i] = ans[j];
                    break;
                } else {
                    deny[ans[j] - 'a'] = true;
                }
            }
            if (!got) {
                for (char c = 'a'; c <= 'z'; c++) {
                    if(!deny[c-'a']){
                        ans[i] = c;
                        got = true;
                        break;
                    }
                }
            }

            if(!got) return "";
        }

        if(!validate(lcp, ans)) return "";

        return new String(ans);
    }
}