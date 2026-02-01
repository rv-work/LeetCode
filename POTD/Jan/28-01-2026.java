
// class Solution {

//     boolean check(int i, int j, int n, int m) {
//         return i >= 0 && i < n && j >= 0 && j < m;
//     }

//     int res(int i, int j, int[][] g, int k, Map<List<Integer>, List<int[]>> map) {
//         int n = g.length;
//         int m = g[0].length;

//         if (i == n - 1 && j == m - 1){
//             return 0; // kyunki yha phuchne ki cost mai aane se phle de chuka hu...
//         }

//         int right = Integer.MAX_VALUE;
//         int down = Integer.MAX_VALUE;
//         int bestTeleportation = Integer.MAX_VALUE;

//         if (check(i, j + 1, n, m))
//             right = g[i][j+1] + res(i, j + 1, g, k, map);
//         if (check(i + 1, j, n, m))
//             down =  g[i+1][j] +  res(i + 1, j, g, k, map);

//         if (k > 0) {
//             for (int[] arr : map.get(Arrays.asList(i, j))) {
//                 bestTeleportation = Math.min(bestTeleportation, res(arr[0], arr[1], g, k - 1, map));
//             }
//         }

//         return  Math.min(bestTeleportation , Math.min(down , right));

//     }

//     public int minCost(int[][] g, int k) {

//         Map<List<Integer>, List<int[]>> map = new HashMap<>();

//         int n = g.length;
//         int m = g[0].length;

//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < m; j++) {

//                 // Create key for (i,j)
//                 List<Integer> key = Arrays.asList(i, j);

//                 // Create an empty list for this key
//                 map.put(key, new ArrayList<>());

//                 // Now fill all (x,y) such that g[x][y] <= g[i][j]
//                 for (int x = i; x < n; x++) {
//                     int y = x==i? x : 0;
//                     while(y<m) {

//                         if (g[x][y] <= g[i][j]) {
//                             map.get(key).add(new int[] { x, y });
//                         }

//                         y++;
//                     }
//                 }
//             }
//         }

//         return res(0, 0, g, k, map);

//     }
// }





// lets apply dp.....




// class Solution {

//     boolean check(int i, int j, int n, int m) {
//         return i >= 0 && i < n && j >= 0 && j < m;
//     }

//     int res(int i, int j, int[][] g, int k, Map<List<Integer>, List<int[]>> map, int[][][] dp) {
//         int n = g.length;
//         int m = g[0].length;

//         if (i == n - 1 && j == m - 1) {
//             return 0;
//         }

//         if (dp[i][j][k] != -1) return dp[i][j][k];

//         int right = Integer.MAX_VALUE;
//         int down = Integer.MAX_VALUE;
//         int bestTeleportation = Integer.MAX_VALUE;

//         // Normal moves
//         if (check(i, j + 1, n, m)) {
//             int val = res(i, j + 1, g, k, map, dp);
//             if(val != Integer.MAX_VALUE) right = g[i][j + 1] + val;
//         }
//         if (check(i + 1, j, n, m)) {
//             int val = res(i + 1, j, g, k, map, dp);
//             if(val != Integer.MAX_VALUE) down = g[i + 1][j] + val;
//         }

//         // Teleportation
//         if (k > 0) {
//             List<Integer> key = Arrays.asList(i, j);
//             if (map.containsKey(key)) {
//                 for (int[] arr : map.get(key)) {
//                     bestTeleportation = Math.min(bestTeleportation, res(arr[0], arr[1], g, k - 1, map, dp));
//                 }
//             }
//         }

//         return dp[i][j][k] = Math.min(bestTeleportation, Math.min(down, right));
//     }

//     public int minCost(int[][] g, int k) {
//         Map<List<Integer>, List<int[]>> map = new HashMap<>();
//         int n = g.length;
//         int m = g[0].length;

//         // Precompute valid teleport destinations
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < m; j++) {
                
//                 List<Integer> key = Arrays.asList(i, j);
//                 map.put(key, new ArrayList<>());

//                 // FIX: Check ALL cells in the grid (0 to n, 0 to m)
//                 // We must be able to jump UP or LEFT if the value condition is met.
//                 for (int x = 0; x < n; x++) {     
//                     for (int y = 0; y < m; y++) { 

//                         if (x == i && y == j) continue; // Skip self

//                         if (g[x][y] <= g[i][j]) {
//                             map.get(key).add(new int[] { x, y });
//                         }
//                     }
//                 }
//             }
//         }

//         int[][][] dp = new int[n][m][k + 1];
//         for (int[][] row : dp) {
//             for (int[] col : row) {
//                 Arrays.fill(col, -1);
//             }
//         }

//         return res(0, 0, g, k, map, dp);
//     }
// }

// ab iss code me ye problem hai ki ...hm  traverse kr rhe hain pure cells me ... very costly..










// here we are doing with string conversion..


// class Solution {

//     boolean check(int i, int j, int n, int m) {
//         return i >= 0 && i < n && j >= 0 && j < m;
//     }

//     int res(int i, int j, int[][] g, int k, Map<String, List<int[]>> map, int[][][] dp) {
//         int n = g.length;
//         int m = g[0].length;

//         // Base case: Reached bottom-right
//         if (i == n - 1 && j == m - 1) {
//             return 0;
//         }

//         if (dp[i][j][k] != -1) return dp[i][j][k];

//         int right = Integer.MAX_VALUE;
//         int down = Integer.MAX_VALUE;
//         int bestTeleportation = Integer.MAX_VALUE;

//         // 1. Normal Moves (Right & Down) -> Cost = value of destination cell
//         if (check(i, j + 1, n, m)) {
//             int val = res(i, j + 1, g, k, map, dp);
//             if (val != Integer.MAX_VALUE)
//                 right = g[i][j + 1] + val;
//         }
            
//         if (check(i + 1, j, n, m)) {
//             int val = res(i + 1, j, g, k, map, dp);
//             if (val != Integer.MAX_VALUE)
//                 down = g[i + 1][j] + val;
//         }

//         // 2. Teleportation -> Cost = 0 (based on your logic/output)
//         if (k > 0) {
//             String key = i + "," + j;
//             if (map.containsKey(key)) {
//                 for (int[] arr : map.get(key)) {
//                     // Reduce k by 1
//                     bestTeleportation = Math.min(bestTeleportation, res(arr[0], arr[1], g, k - 1, map, dp));
//                 }
//             }
//         }

//         return dp[i][j][k] = Math.min(bestTeleportation, Math.min(down, right));
//     }

//     public int minCost(int[][] g, int k) {
//         // Use String key "i,j" instead of List to avoid overhead/complexity
//         Map<String, List<int[]>> map = new HashMap<>();

//         int n = g.length;
//         int m = g[0].length;

//         // Precompute valid teleport destinations
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < m; j++) {
                
//                 String key = i + "," + j;
//                 map.put(key, new ArrayList<>());

//                 // FIX: Check ALL cells in the grid, not just future ones.
//                 // Teleportation allows going anywhere (even backwards) if value condition is met.
//                 for (int x = 0; x < n; x++) {
//                     for (int y = 0; y < m; y++) {
                        
//                         // Optimization: Don't teleport to yourself
//                         if (x == i && y == j) continue;

//                         if (g[x][y] <= g[i][j]) {
//                             map.get(key).add(new int[]{x, y});
//                         }
//                     }
//                 }
//             }
//         }

//         int[][][] dp = new int[n][m][k + 1];
//         for (int[][] row : dp) {
//             for (int[] col : row) {
//                 Arrays.fill(col, -1);
//             }
//         }

//         return res(0, 0, g, k, map, dp);
//     }
// }








// still not enough lets pre compute ..



// import java.util.*;

// class Solution {

//     int getBestTele(int i, int j, int rankIdx, int[][] ranks, List<int[]> cells, int[][] g, int k) {

//         int best = Integer.MAX_VALUE;
     

//         for(int r = 0; r<=rankIdx; r++){ 
//             int[] cell = cells.get(r);
//             int x = cell[1];
//             int y = cell[2];
            
//             if (x == i && y == j) continue;

//             int val = res(x, y, ranks, cells, g, k - 1);
//             if (val != Integer.MAX_VALUE) {
//                 best = Math.min(best, val);
//             }

//         }
        
      

//         return best;
//     }

//     int res(int i, int j, int[][] rank, List<int[]> cells, int[][] g, int k) {
//         int n = g.length;
//         int m = g[0].length;

//         if (i == n - 1 && j == m - 1) return 0;

//         int down = Integer.MAX_VALUE;
//         int right = Integer.MAX_VALUE;
//         int bestT = Integer.MAX_VALUE;

//         if (i + 1 < n) {
//             int val = res(i + 1, j, rank, cells, g, k);
//             if (val != Integer.MAX_VALUE) {
//                 down = g[i + 1][j] + val;
//             }
//         }
//         if (j + 1 < m) {
//             int val = res(i, j + 1, rank, cells, g, k);
//             if (val != Integer.MAX_VALUE) {
//                 right = g[i][j + 1] + val;
//             }
//         }

//         if (k > 0)
//             bestT = getBestTele(i, j, rank[i][j], rank, cells, g, k);

//         return Math.min(bestT, Math.min(down, right));
//     }

//     public int minCost(int[][] g, int k) {
//         int n = g.length;
//         int m = g[0].length;

//         List<int[]> cells = new ArrayList<>();
//         int[][] rank = new int[n][m];

//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < m; j++) {
//                 cells.add(new int[]{g[i][j], i, j});
//             }
//         }

//         Collections.sort(cells, (a, b) -> Integer.compare(a[0], b[0]));

//         int r = 0;
//         for (int[] cell : cells) {
//             int x = cell[1];
//             int y = cell[2];
//             rank[x][y] = r++;
//         }

//         return res(0, 0, rank, cells, g, k);
//     }
// }




//isme problem ye hai ki.. see.. maan lo ek value hai 5.. ab ye 5 multiple times hai ....now hmari 5.. in 5 ki series me first hai to ...ab iski jo rank hogi ...uske aage nhi jayenge hm jabki hme jana chahiye ..kyunki = is allowed...right so may be un par jane pr hme behtar ans mike so thats why we need to go there.......right .......now for that....i need to go until... i am not getting the next bigger value .. so for that... lets go ...till this... all values.....








// lets resolve this..


// class Solution {

//     int getBestTele(int i, int j, List<int[]> cells, int[][] g, int k, int[][] bestValue, int[][][] dp) {
//         int best = Integer.MAX_VALUE;
//         int myVal = g[i][j];

//         if (bestValue[myVal][k] != -1) return bestValue[myVal][k];

//         for (int[] cell : cells) {
//             int val = cell[0];
//             int x = cell[1];
//             int y = cell[2];

//             // Stop if value exceeds current (Sorted List)
//             if (val > myVal) break;

//             int resVal = res(x, y, cells, g, k - 1, bestValue, dp);

//             if (resVal != Integer.MAX_VALUE) {
//                 best = Math.min(best, resVal);
//             }
//         }

//         return bestValue[myVal][k] = best;
//     }

//     int res(int i, int j, List<int[]> cells, int[][] g, int k, int[][] bestValue, int[][][] dp) {
//         int n = g.length;
//         int m = g[0].length;

//         // Base case: reaching bottom-right
//         if (i == n - 1 && j == m - 1) return 0;

//         if (dp[i][j][k] != -1) return dp[i][j][k];

//         int down = Integer.MAX_VALUE;
//         int right = Integer.MAX_VALUE;
//         int bestT = Integer.MAX_VALUE;

//         // FIX 2: Check for Overflow before adding cost
//         if (i + 1 < n) {
//             int val = res(i + 1, j, cells, g, k, bestValue, dp);
//             if (val != Integer.MAX_VALUE) {
//                 down = g[i + 1][j] + val;
//             }
//         }

//         if (j + 1 < m) {
//             int val = res(i, j + 1, cells, g, k, bestValue, dp);
//             if (val != Integer.MAX_VALUE) {
//                 right = g[i][j + 1] + val;
//             }
//         }

//         if (k > 0) {
//             bestT = getBestTele(i, j, cells, g, k, bestValue, dp);
//         }

//         return dp[i][j][k] = Math.min(bestT, Math.min(down, right));
//     }

//     public int minCost(int[][] g, int k) {
//         int n = g.length;
//         int m = g[0].length;

//         List<int[]> cells = new ArrayList<>();
//         int max = Integer.MIN_VALUE;

//         int[][][] dp = new int[n][m][k + 1];

//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < m; j++) {
//                 max = Math.max(max, g[i][j]);
//                 Arrays.fill(dp[i][j], -1);
//                 cells.add(new int[]{g[i][j], i, j});
//             }
//         }

//         int[][] bestValue = new int[max + 1][k + 1];
//         for (int[] arr : bestValue) Arrays.fill(arr, -1);

//         Collections.sort(cells, (a, b) -> Integer.compare(a[0], b[0]));

//         return res(0, 0, cells, g, k, bestValue, dp);
//     }
// }




// ab is code me problem hye hai ki bestValueme max size ka bna rhe joki pta nhi kitna bda ho skta...to isse bachenge aur hm hr baar pura ka pura iterate nhi krenge vo bhi 1 ke liye srai cells me jana bahot costly ho rha tha ... to vo shi krenge ab .....


// Helper structures for O(1) group lookup
// Map<Integer, Integer> valToIndex;       // Maps actual value -> index in uniqueValues list
// List<Integer> uniqueValues;             // Sorted list of unique values
// Map<Integer, List<int[]>> valToCells;   // Maps actual value -> list of cell coordinates




//// Final Version............................................................................................

class Solution {

    int getBestTele(int valIdx, int[][] g, int k,
            int[][] bestValue,
            Map<Integer, List<int[]>> valToCells,
            Map<Integer, Integer> valToIndex,
            List<Integer> uniqueValues,
            int[][][] dp){
            
            int best = Integer.MAX_VALUE;

            if(valIdx < 0) return best;

            if(bestValue[valIdx][k] != -1) return bestValue[valIdx][k];

            int currentVal = uniqueValues.get(valIdx);

            // process for this currentVal.. ==
            for(int [] cell : valToCells.get(currentVal)){
                int x  = cell[0];
                int y  = cell[1];

                best = Math.min(best , res(x, y, g, k-1, dp, bestValue, valToCells, valToIndex, uniqueValues));
            }

            // process for smaller values ..<..
            best = Math.min(best , 
                            getBestTele(valIdx-1, g, k, bestValue, valToCells, valToIndex, uniqueValues, dp));
                                       // valIdx - 1...means smaller values... hongi ab isme sirf..

        return bestValue[valIdx][k] = best;
    }

    int res(int i, int j, int[][] g, int k,
            int[][][] dp, int[][] bestValue,
            Map<Integer, List<int[]>> valToCells,
            Map<Integer, Integer> valToIndex,
            List<Integer> uniqueValues) {

        int n = g.length;
        int m = g[0].length;

        // Reached bottom-right
        if (i == n - 1 && j == m - 1)
            return 0;

        if (dp[i][j][k] != -1)
            return dp[i][j][k];

        int ans = Integer.MAX_VALUE;

        // Move Down
        if (i + 1 < n) {
            int val = res(i + 1, j, g, k, dp, bestValue, valToCells, valToIndex, uniqueValues);
            if (val != Integer.MAX_VALUE) {
                ans = Math.min(ans, g[i + 1][j] + val);
            }
        }

        // Move Right
        if (j + 1 < m) {
            int val = res(i, j + 1, g, k, dp, bestValue, valToCells, valToIndex, uniqueValues);
            if (val != Integer.MAX_VALUE) {
                ans = Math.min(ans, g[i][j + 1] + val);
            }
        }

        // Teleport (only if k > 0)
        if (k > 0) {
            int valIdx = valToIndex.get(g[i][j]);
            int teleCost = getBestTele(valIdx, g, k, bestValue, valToCells, valToIndex, uniqueValues, dp);
            ans = Math.min(ans, teleCost);
        }

        return dp[i][j][k] = ans;
    }

    public int minCost(int[][] g, int k) {
        int n = g.length;
        int m = g[0].length;

        Map<Integer, Integer> valToIndex = new HashMap<>();
        List<Integer> uniqueValues = new ArrayList<>();
        Map<Integer, List<int[]>> valToCells = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int v = g[i][j];

                // group cells by value
                valToCells.computeIfAbsent(v, x -> new ArrayList<>());
                valToCells.get(v).add(new int[] { i, j });

            }
        }

        uniqueValues = new ArrayList<>(valToCells.keySet());

        Collections.sort(uniqueValues);

        for (int i = 0; i < uniqueValues.size(); i++) {
            valToIndex.put(uniqueValues.get(i), i);
        }

        int[][][] dp = new int[n][m][k + 1];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                Arrays.fill(dp[i][j], -1);

        int[][] bestValue = new int[uniqueValues.size()][k + 1];
        for (int[] arr : bestValue)
            Arrays.fill(arr, -1);

        return res(0, 0, g, k, dp, bestValue, valToCells, valToIndex, uniqueValues);
    }

}
