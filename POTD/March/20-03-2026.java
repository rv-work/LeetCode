class Solution {
    public int[][] minAbsDiff(int[][] g, int k) {
        int n = g.length;
        int m = g[0].length;

        int ans[][] = new int[n - k + 1][m - k + 1];

        for (int i = 0; i < n - k + 1; i++) {
            for (int j = 0; j < m - k + 1; j++) {

                List<Integer> li = new ArrayList<>();
                for (int x = i; x < i + k; x++) {
                    for (int y = j; y < j + k; y++) {
                        int num = g[x][y];
                        li.add(num);
                    }
                }

                Collections.sort(li);
                int min = Integer.MAX_VALUE;
                for (int x = 1; x < li.size(); x++) {
                    int current = li.get(x);
                    int previous = li.get(x - 1);
                    
                    if (current != previous) {
                        min = Math.min(min, Math.abs(current - previous));
                    }
                }

                ans[i][j] = (min == Integer.MAX_VALUE) ? 0 : min;

            }
        }

        return ans;
    }
}