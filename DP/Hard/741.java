class Solution {

    boolean check(int i, int j, int n, int[][] g) {
        return i >= 0 && j >= 0 && i < n && j < n && g[i][j] != -1;
    }

    // For tracking path parent in DOWN journey
    int parentDown[][][];
    // 0 = none, 1 = down, 2 = right

    int goDown(int i, int j, int[][] g) {
        int n = g.length;

        if (i == n - 1 && j == n - 1) {
            parentDown[i][j][0] = 0; 
            return g[i][j];
        }

        int down = -1;
        int right = -1;

        if (check(i + 1, j, n, g))
            down = goDown(i + 1, j, g);
        if (check(i, j + 1, n, g))
            right = goDown(i, j + 1, g);

        if (down < 0 && right < 0)
            return -1;

        if (down >= right) {
            parentDown[i][j][0] = 1; // move DOWN
            return g[i][j] + down;
        } else {
            parentDown[i][j][0] = 2; // move RIGHT
            return g[i][j] + right;
        }
    }

    int goUp(int i, int j, int[][] g) {
        int n = g.length;

        if (i == 0 && j == 0)
            return g[i][j];

        int up = Integer.MIN_VALUE;
        int left = Integer.MIN_VALUE;

        if (check(i - 1, j, n, g))
            up = goUp(i - 1, j, g);
        if (check(i, j - 1, n, g))
            left = goUp(i, j - 1, g);

        int best = Math.max(up, left);

        return g[i][j] + Math.max(0, best);
    }

    List<int[]> buildPathDown(int n) {
        List<int[]> path = new ArrayList<>();
        int i = 0, j = 0;
        path.add(new int[] { i, j });

        while (!(i == n - 1 && j == n - 1)) {
            int move = parentDown[i][j][0];
            if (move == 1)
                i++;
            else
                j++;
            path.add(new int[] { i, j });
        }
        return path;
    }

    public int cherryPickup(int[][] g) {
        int n = g.length;

        parentDown = new int[n][n][1];

        int down = goDown(0, 0, g);
        if (down == -1)
            return 0;

        List<int[]> path1 = buildPathDown(n);

        for (int[] p : path1) {
            g[p[0]][p[1]] = 0;
        }

        int up = goUp(n - 1, n - 1, g);

        return down + up;
    }
}



// this is complete solution...... but this does not work
// optimal is = optimal down + optimal up
// but here down optimal krne me hm up ki optimality khoo dete hai ...

// so not always it gives correct ans .... so hme dono ek sath krne pdeneg 