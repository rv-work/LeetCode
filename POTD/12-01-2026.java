class Solution {
  public int minTimeToVisitAllPoints(int[][] points) {
    int n = points.length;
    int ans = 0;
    for (int i = 0; i < n - 1; i++) {
      int x = Math.abs(points[i + 1][0] - points[i][0]);
      int y = Math.abs(points[i + 1][1] - points[i][1]);
      int min = Math.min(x, y);
      ans += x + y - min; // ans += min , ans += x-min , ans += y-min
    }

    return ans;
  }
}

class Solution {
  public int minTimeToVisitAllPoints(int[][] p) {
    int ans = 0;
    for (int i = 1; i < p.length; i++) {
      ans += Math.max(
          Math.abs(p[i][0] - p[i - 1][0]),
          Math.abs(p[i][1] - p[i - 1][1])

      );
    }
    return ans;
  }
}

// (p[i,0] - p[i-1][0]) x or pre ke x ka difference
// (p[i,1] - p[i-1][1]) y or pre ke y ka diff
