class Solution {
    int res(String s, int i, int j) {

        if (i > j) return 0;
        if (i == j) return 1;

        if (s.charAt(i) == s.charAt(j))
            return 2 + res(s, i + 1, j - 1);

        return Math.max(
            res(s, i + 1, j),
            res(s, i, j - 1)
        );
    }

    public int longestPalindromeSubseq(String s) {
        return res(s, 0, s.length() - 1);
    }
}