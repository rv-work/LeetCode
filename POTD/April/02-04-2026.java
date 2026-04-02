class Solution {
  int n, m;
  Integer dp[][][];

  int rec(int i, int j, int save, int[][] c) {

    if (i == n - 1 && j == m - 1) {
      if (c[i][j] < 0 && save > 0)
        return 0;
      return c[i][j];
    }

    if (dp[i][j][save] != null)
      return dp[i][j][save];

    int amt = c[i][j];
    int down = Integer.MIN_VALUE;
    int right = Integer.MIN_VALUE;

    if (amt < 0) {

      // use power (skip loss)
      if (save > 0) {
        if (i + 1 < n) {
          int res = rec(i + 1, j, save - 1, c);
          if (res != Integer.MIN_VALUE)
            down = Math.max(down, res);
        }
        if (j + 1 < m) {
          int res = rec(i, j + 1, save - 1, c);
          if (res != Integer.MIN_VALUE)
            right = Math.max(right, res);
        }
      }

      // don't use power (take loss)
      if (i + 1 < n) {
        int res = rec(i + 1, j, save, c);
        if (res != Integer.MIN_VALUE)
          down = Math.max(down, amt + res);
      }
      if (j + 1 < m) {
        int res = rec(i, j + 1, save, c);
        if (res != Integer.MIN_VALUE)
          right = Math.max(right, amt + res);
      }

    } else {

      // normal case
      if (i + 1 < n) {
        int res = rec(i + 1, j, save, c);
        if (res != Integer.MIN_VALUE)
          down = amt + res;
      }
      if (j + 1 < m) {
        int res = rec(i, j + 1, save, c);
        if (res != Integer.MIN_VALUE)
          right = amt + res;
      }
    }

    return dp[i][j][save] = Math.max(down, right);
  }

  public int maximumAmount(int[][] c) {
    n = c.length;
    m = c[0].length;
    dp = new Integer[n][m][3];
    return rec(0, 0, 2, c);
  }
}