class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int n = bottomLeft.length;
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int x1 = bottomLeft[i][0];
            int y1 = bottomLeft[i][1];
            int x2 = topRight[i][0];
            int y2 = topRight[i][1];

            for (int j = i + 1; j < n; j++) {

                int a1 = bottomLeft[j][0];
                int b1 = bottomLeft[j][1];
                int a2 = topRight[j][0];
                int b2 = topRight[j][1];

                // intersection boundaries
                int left = Math.max(x1, a1);
                int right = Math.min(x2, a2);
                int bottom = Math.max(y1, b1);
                int top = Math.min(y2, b2);

                // no overlap
                if (right <= left || top <= bottom) continue;

                // intersection width and height
                int width = right - left;
                int height = top - bottom;

                int side = Math.min(width, height);
                ans = Math.max(ans, (long) side * side);
            }
        }
        return ans;
    }
}
