class Solution {

    String res(int i, int j, String s1, String s2, String[][] dp) {
        int n = s1.length();
        int m = s2.length();
        
        if (i >= n || j >= m) return "";

        if (dp[i][j] != null) return dp[i][j];

        StringBuilder ans = new StringBuilder();
        
        if (s1.charAt(i) == s2.charAt(j)) {
            ans = s1.charAt(i) + res(i + 1, j + 1, s1, s2, dp);
        } else {
            String moveI = res(i + 1, j, s1, s2, dp);
            String moveJ = res(i, j + 1, s1, s2, dp);
            
            ans = (moveI.length() > moveJ.length()) ? moveI : moveJ;
        }

        return dp[i][j] = ans;
    }

    public String shortestCommonSupersequence(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        
        String[][] dp = new String[n][m];
        String lcs = res(0, 0, str1, str2, dp);

        int j1 = 0; 
        int j2 = 0; 
        int k1 = 0; 
        int k2 = 0; 
        String ans = "";
        
        for (int i = 0; i < lcs.length(); i++) {
            char ch = lcs.charAt(i);

            while (str1.charAt(j2) != ch) j2++;
            
            while (str2.charAt(k2) != ch) k2++;

            ans += str1.substring(j1, j2);
            ans += str2.substring(k1, k2);
            
            ans += ch;

            j1 = j2 + 1;
            j2 = j1; 
            k1 = k2 + 1;
            k2 = k1;
        }

        ans += str1.substring(j1, n);
        ans += str2.substring(k1, m);

        return ans;

    }
}





class Solution {

    StringBuilder res(int i, int j, String s1, String s2, StringBuilder[][] dp) {
        int n = s1.length();
        int m = s2.length();

        if (i >= n || j >= m) return new StringBuilder();

        if (dp[i][j] != null) return dp[i][j];

        StringBuilder ans;

        if (s1.charAt(i) == s2.charAt(j)) {
            ans = new StringBuilder();
            ans.append(s1.charAt(i));
            ans.append(res(i + 1, j + 1, s1, s2, dp));
        } else {
            StringBuilder moveI = res(i + 1, j, s1, s2, dp);
            StringBuilder moveJ = res(i, j + 1, s1, s2, dp);

            ans = (moveI.length() > moveJ.length())
                    ? new StringBuilder(moveI)
                    : new StringBuilder(moveJ);
        }

        return dp[i][j] = ans;
    }

    public String shortestCommonSupersequence(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        StringBuilder[][] dp = new StringBuilder[n][m];

        StringBuilder lcs = res(0, 0, str1, str2, dp);

        int j1 = 0, j2 = 0;
        int k1 = 0, k2 = 0;

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < lcs.length(); i++) {
            char ch = lcs.charAt(i);

            while (str1.charAt(j2) != ch) j2++;
            while (str2.charAt(k2) != ch) k2++;

            ans.append(str1.substring(j1, j2));
            ans.append(str2.substring(k1, k2));
            ans.append(ch);

            j1 = j2 + 1;
            j2 = j1;

            k1 = k2 + 1;
            k2 = k1;
        }

        ans.append(str1.substring(j1, n));
        ans.append(str2.substring(k1, m));

        return ans.toString();
    }
}




// ye kaam nhi kreag kyunki.. stringbuilder me memory jyada lg jayegi kyunki... ye mutable hota hai .....








class Solution {
    public String shortestCommonSupersequence(String s1, String s2) {
        int n = s1.length(), m = s2.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j))
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
            }
        }

        //  Build SCS using only pointers
        int i = 0, j = 0;
        StringBuilder ans = new StringBuilder();

        while (i < n && j < m) {
            if (s1.charAt(i) == s2.charAt(j)) {
                ans.append(s1.charAt(i));
                i++; j++;
            } else {
                if (dp[i + 1][j] > dp[i][j + 1]) {
                    ans.append(s1.charAt(i));
                    i++;
                } else {
                    ans.append(s2.charAt(j));
                    j++;
                }
            }
        }

        // Add leftovers
        ans.append(s1.substring(i));
        ans.append(s2.substring(j));

        return ans.toString();
    }
}





// SUMMARY OF WHY IT WORKS
//  Case 1: match → add once (common part)
//  Case 2: mismatch → choose the better LCS direction
//  Ensures both strings appear inside
//  Ensures shortest length because we follow LCS
//  Final leftover parts are appended
