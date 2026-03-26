
class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        long row[] = new long[n];
        long col[] = new long[m];

        Map<Integer, Set<Integer>> r = new HashMap<>();
        Map<Integer, Set<Integer>> c = new HashMap<>();

        long sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum += grid[i][j];

                r.putIfAbsent(grid[i][j], new TreeSet<>());
                r.get(grid[i][j]).add(i);

                c.putIfAbsent(grid[i][j], new TreeSet<>());
                c.get(grid[i][j]).add(j);
            }
        }

        for (int i = 0; i < n; i++) {
            long rowSum = 0;
            for (int j = 0; j < m; j++) {
                rowSum += grid[i][j];
            }
            row[i] = rowSum;
        }

        for (int i = 0; i < m; i++) {
            long colSum = 0;
            for (int j = 0; j < n; j++) {
                colSum += grid[j][i];
            }
            col[i] = colSum;
        }

        long pre = 0;
        
        // ================= ROW CUTS =================
        for (int i = 0; i < n - 1; i++) {
            pre += row[i];
            long diff = sum - 2 * pre;

            if (diff == 0) return true;
            
            // Impossible difference check (grid max val is 100,000)
            if (Math.abs(diff) > 100000) continue;

            TreeSet<Integer> set = (TreeSet<Integer>) r.get((int) Math.abs(diff));

            if (diff > 0) { // Bottom is larger (Rows i+1 to n-1)
                if (set != null && set.last() > i) {
                    if (m == 1) { // Explicit 1D Column handling
                        if (grid[i + 1][0] == diff || grid[n - 1][0] == diff) return true;
                    } else if (n - 1 - i > 1) { 
                        return true;
                    } else { 
                        //  Check bottom corners, NOT top corners!
                        if (grid[n - 1][0] == (int) diff || grid[n - 1][m - 1] == (int) diff) return true;
                    }
                }
            } else { // Top is larger (Rows 0 to i)
                diff = -diff;
                //  Must be '<=' instead of '<'
                if (set != null && set.first() <= i) {
                    if (m == 1) {
                        if (grid[0][0] == diff || grid[i][0] == diff) return true;
                    } else if (i >= 1) { // i >= 1 means >= 2 rows
                        return true;
                    } else { 
                        if (grid[0][0] == (int) diff || grid[0][m - 1] == (int) diff) return true;
                    }
                }
            }
        }

        pre = 0;
        // ================= COLUMN CUTS =================
        for (int i = 0; i < m - 1; i++) {
            pre += col[i];
            long diff = sum - 2 * pre;
            
            if (diff == 0) return true;

            //  Impossible difference check
            if (Math.abs(diff) > 100000) continue;

            TreeSet<Integer> set = (TreeSet<Integer>) c.get((int) Math.abs(diff));

            if (diff > 0) { // Right is larger (Cols i+1 to m-1)
                if (set != null && set.last() > i) {
                    if (n == 1) { // Explicit 1D Row handling
                        if (grid[0][i + 1] == diff || grid[0][m - 1] == diff) return true;
                    } else if (m - 1 - i > 1) { 
                        return true;
                    } else { 
                        //  Check Top-Right and Bottom-Right corners
                        if (grid[0][m - 1] == (int) diff || grid[n - 1][m - 1] == (int) diff) return true;
                    }
                }
            } else { // Left is larger (Cols 0 to i)
                diff = -diff;
                // : Must be '<=' instead of '<'
                if (set != null && set.first() <= i) {
                    if (n == 1) { 
                        if (grid[0][0] == diff || grid[0][i] == diff) return true;
                    } else if (i >= 1) { 
                        return true;
                    } else { 
                        if (grid[0][0] == (int) diff || grid[n - 1][0] == (int) diff) return true;
                    }
                }
            }
        }

        return false;
    }
}