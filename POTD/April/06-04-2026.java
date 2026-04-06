class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        Set<String> set = new HashSet<>();
        for (int[] ob : obstacles) {
            set.add(ob[0] + "," + ob[1]);
        }

        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
        int di = 0;

        int x = 0, y = 0;
        int max = 0;

        for (int c : commands) {
            if (c == -2) di = (di + 3) % 4; // left
            else if (c == -1) di = (di + 1) % 4; // right
            else {
                while (c-- > 0) {
                    int nx = x + dirs[di][0];
                    int ny = y + dirs[di][1];

                    if (set.contains(nx + "," + ny)) break;

                    x = nx;
                    y = ny;

                    max = Math.max(max, x*x + y*y);
                }
            }
        }

        return max;
    }
}