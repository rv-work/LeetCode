
// class Solution {

//     long flat(int x, int y, int side) {
//         if (y == 0) return x; 
//         if (x == side) return (long)side + y; 
//         if (y == side) return 2L * side + (side - x); 
//         return 3L * side + (side - y); 
//     }

//     boolean isValid(long m, long[] A, int n, int k, int side) {
//         long perimeter = 4L * side;
        
//         for (int i = 0; i < n; i++) {
//             int curr = i;
//             int count = 1; 
            
//             for (int jumps = 0; jumps < k - 1; jumps++) {
//                 int next = (curr + 1) % n;
//                 long dist = 0;
                
//                 while (next != i) {
//                     dist = A[next] - A[curr];
//                     if (dist < 0) dist += perimeter;
                    
//                     if (dist >= m) break; 
//                     next = (next + 1) % n;
//                 }
                
//                 curr = next;
//                 if (curr == i) break; 
//                 count++;
//             }
            
            
//             if (count == k) {
//                 long lastDist = A[i] - A[curr];
//                 if (lastDist < 0) lastDist += perimeter;
//                 if (lastDist >= m) return true;
//             }
//         }
//         return false;
//     }

//     public int maxDistance(int side, int[][] points, int k) {
//         int n = points.length;
//         long[] A = new long[n];
        
//         for (int i = 0; i < n; i++) {
//             A[i] = flat(points[i][0], points[i][1], side);
//         }
//         Arrays.sort(A);
        
//         long l = 1, r = side; 
//         long ans = 0;
        
//         while (l <= r) {
//             long m = l + (r - l) / 2;
//             if (isValid(m, A, n, k, side)) {
//                 ans = m;
//                 l = m + 1; 
//             } else {
//                 r = m - 1;
//             }
//         }
//         return (int) ans;
//     }

    
// }











// class Solution {
//     long flat(int x, int y, int side) {
//         if (y == 0) return x;
//         if (x == side) return (long) side + y;
//         if (y == side) return 2L * side + (side - x);
//         return 3L * side + (side - y);
//     }

//     boolean isValid(long m, long[] A, int n, int k, int side) {
//         long perimeter = 4L * side;

//         long[] B = new long[2 * n];
//         for (int i = 0; i < n; i++) {
//             B[i] = A[i];
//             B[i + n] = A[i] + perimeter;
//         }

//         for (int i = 0; i < n; i++) {
//             int curr = i;
//             int count = 1;

//             for (int jumps = 0; jumps < k - 1; jumps++) {
//                 int next = curr + 1;

//                 while (next < i + n) {
//                     long dist = B[next] - B[curr];
//                     if (dist >= m) break;
//                     next++;
//                 }

//                 if (next == i + n) break;

//                 curr = next;
//                 count++;
//             }

//             if (count == k) {
//                 long lastDist = B[i] + perimeter - B[curr];
//                 if (lastDist >= m) return true;
//             }
//         }

//         return false;
//     }

//     public int maxDistance(int side, int[][] points, int k) {
//         int n = points.length;
//         long[] A = new long[n];

//         for (int i = 0; i < n; i++) {
//             A[i] = flat(points[i][0], points[i][1], side);
//         }

//         Arrays.sort(A);

//         long l = 1, r = side;
//         long ans = 0;

//         while (l <= r) {
//             long m = l + (r - l) / 2;
//             if (isValid(m, A, n, k, side)) {
//                 ans = m;
//                 l = m + 1;
//             } else {
//                 r = m - 1;
//             }
//         }

//         return (int) ans;
//     }

// }













// class Solution {

//     long flat(int x, int y, int side) {
//         if (y == 0) return x; 
//         if (x == side) return (long)side + y; 
//         if (y == side) return 2L * side + (side - x); 
//         return 3L * side + (side - y); 
//     }

//     boolean isValid(long m, long[] A, int n, int k, int side) {
//         int n2 = 2 * n;
//         int[] next = new int[n2];
//         int j = 0;
        
//         for (int i = 0; i < n2; i++) {
//             while (j < n2 && A[j] - A[i] < m) {
//                 j++;
//             }
//             next[i] = j; 
//         }
        
//         int LOG = 32 - Integer.numberOfLeadingZeros(k);
//         int[][] up = new int[LOG][n2 + 1];
        
//         for (int i = 0; i < n2; i++) {
//             up[0][i] = next[i];
//         }
//         up[0][n2] = n2;
        
//         for (int step = 1; step < LOG; step++) {
//             for (int i = 0; i <= n2; i++) {
//                 up[step][i] = up[step - 1][up[step - 1][i]];
//             }
//         }
        
//         long maxAllowed = 4L * side - m; 
        
//         for (int i = 0; i < n; i++) {
//             int curr = i;
//             int jumps = k - 1; 
            
//             for (int step = 0; step < LOG; step++) {
//                 if ((jumps & (1 << step)) != 0) {
//                     curr = up[step][curr];
//                 }
//             }
            
//             if (curr < n2 && A[curr] - A[i] <= maxAllowed) {
//                 return true; 
//             }
//         }
//         return false;
//     }

//     public int maxDistance(int side, int[][] points, int k) {
//         int n = points.length;
//         long[] A = new long[n];
        
//         for (int i = 0; i < n; i++) {
//             A[i] = flat(points[i][0], points[i][1], side);
//         }
//         Arrays.sort(A);
        
//         long[] A2 = new long[2 * n];
//         for (int i = 0; i < n; i++) {
//             A2[i] = A[i];
//             A2[i + n] = A[i] + 4L * side; 
//         }
        
//         long l = 1, r = side; 
//         long ans = 0;
        
//         while (l <= r) {
//             long m = l + (r - l) / 2;
//             if (isValid(m, A2, n, k, side)) {
//                 ans = m;
//                 l = m + 1; 
//             } else {
//                 r = m - 1;
//             }
//         }
//         return (int) ans;
//     }

   
// }














class Solution {

    long flat(int x, int y, int side) {
        if (y == 0) return x; 
        if (x == side) return (long)side + y; 
        if (y == side) return 2L * side + (side - x); 
        return 3L * side + (side - y); 
    }

    boolean isValidV4(long m, long[] A, int n, int k, int side) {
        int n2 = 2 * n;
        int[] next = new int[n2];
        int j = 0;
        
        for (int i = 0; i < n2; i++) {
            while (j < n2 && A[j] - A[i] < m) {
                j++;
            }
            next[i] = j; 
        }
        
        long maxAllowed = 4L * side - m; 
        
 
        int limit = next[0]; 
        
        for (int i = 0; i < limit; i++) {
            int curr = i;
            int count = 1; 
            
            while (count < k && curr < n2) {
                curr = next[curr];
                count++;
            }
            
            if (count == k && curr < n2 && A[curr] - A[i] <= maxAllowed) {
                return true; 
            }
        }
        return false;
    }




    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long[] A = new long[n];
        
        for (int i = 0; i < n; i++) {
            A[i] = flat(points[i][0], points[i][1], side);
        }
        Arrays.sort(A);
        
        long[] A2 = new long[2 * n];
        for (int i = 0; i < n; i++) {
            A2[i] = A[i];
            A2[i + n] = A[i] + 4L * side; 
        }
        
        long l = 1, r = side; 
        long ans = 0;
        
        while (l <= r) {
            long m = l + (r - l) / 2;
            if (isValidV4(m, A2, n, k, side)) {
                ans = m;
                l = m + 1; 
            } else {
                r = m - 1;
            }
        }
        return (int) ans;
    }

    
}