class Solution {
    int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    public void bfs(int[][] image, int i, int j, int color, int originalColor) {
        Queue<int[]> q = new LinkedList<>();
        image[i][j] = color; // change color
        q.offer(new int[] { i, j });
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int[] d : dir) {
                int x = curr[0] + d[0];
                int y = curr[1] + d[1];
                if (x >= 0 && x < image.length && y >= 0 && y < image[0].length && image[x][y] == originalColor) {
                    image[x][y] = color; // change color
                    q.offer(new int[] { x, y });
                }
            }
        }
    }

    public void dfs(int[][] image, int i, int j, int color, int originalColor) {
        image[i][j] = color; // change color
        for (int[] d : dir) {
            int x = i + d[0];
            int y = j + d[1];
            if (x >= 0 && x < image.length && y >= 0 && y < image[0].length && image[x][y] == originalColor) {
                dfs(image, x, y, color, originalColor);
            }
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];
        if (color == originalColor)
            return image; // if original color and cell to be colored is alreay same
        bfs(image, sr, sc, color, originalColor);
        // dfs(image, sr, sc, color, originalColor);
        return image;
    }
}