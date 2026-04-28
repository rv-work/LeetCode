
class Solution {
  public int minOperations(int[][] grid, int x) {
    int r = grid.length;
    int c = grid[0].length;
    int n = r * c;
    int[] a = new int[n];

    int idx = 0;
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        a[idx++] = grid[i][j];
      }
    }

    Arrays.sort(a);

    for (int i = 1; i < n; i++) {
      if ((a[i] - a[0]) % x != 0)
        return -1;
    }

    int mid = a[n / 2];
    int ans = 0;

    for (int val : a) {
      ans += Math.abs(val - mid) / x;
    }

    return ans;
  }
}