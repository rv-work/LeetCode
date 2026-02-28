class Solution {
    int maxStudentsCount = 0;

    int maxStudents(char[][] seats) {
        maxStudentsCount = 0;
        backtrack(seats, 0, 0, 0);
        return maxStudentsCount;
    }

    void backtrack(char[][] seats, int r, int c, int currentStudents) {
        int m = seats.length;
        int n = seats[0].length;

        if (r == m) {
            maxStudentsCount = Math.max(maxStudentsCount, currentStudents);
            return;
        }

        int nextR = (c == n - 1) ? r + 1 : r;
        int nextC = (c == n - 1) ? 0 : c + 1;

        // skip
        backtrack(seats, nextR, nextC, currentStudents);


        // taken
        if (seats[r][c] == '.' && isSafe(seats, r, c)) {
            seats[r][c] = 'S'; 
            backtrack(seats, nextR, nextC, currentStudents + 1);
            seats[r][c] = '.'; 
        }
    }

    private boolean isSafe(char[][] seats, int r, int c) {
        int n = seats[0].length;

        if (c > 0 && seats[r][c - 1] == 'S') return false;

        if (r > 0 && c > 0 && seats[r - 1][c - 1] == 'S') return false;

        if (r > 0 && c < n - 1 && seats[r - 1][c + 1] == 'S') return false;

        return true;
    }
}







class Solution {
    int res(int[] brokenRows, int[][] dp, int row, int prevMask, int m) {
        int n = dp.length;
        if (row == n) return 0;

        if (dp[row][prevMask] != -1) return dp[row][prevMask];

        int maxStudents = 0; 

        for (int currMask = 0; currMask < (1 << m); currMask++) { // trying all the possibilities going from 000..00 to 111..11... like this 

            if ((currMask & (currMask >> 1)) != 0) continue; // ye btata hai ki koi bhi 2 .. 1s sath me to nhi
            
            if ((currMask & brokenRows[row]) != 0) continue; // this one tells ki jha tum bithana chahte ho(1 krna chahte ho vha phle se to 1 ( broken ) nhi hai )
            
            if ((currMask & (prevMask >> 1)) != 0) continue; // shadow on the next place to crash btayega
            if ((currMask & (prevMask << 1)) != 0) continue; // shadow on the prev place to crash btayega

            int cnt = Integer.bitCount(currMask);
            
            int total = cnt + res(brokenRows, dp, row + 1, currMask, m);
            
            maxStudents = Math.max(maxStudents, total);
        }

        return dp[row][prevMask] = maxStudents;
    }

    public int maxStudents(char[][] seats) {
        int n = seats.length;    
        int m = seats[0].length; 
        
        int[][] dp = new int[n][1 << m];
        for (int[] row : dp) Arrays.fill(row, -1);

        int[] brokenRows = new int[n];
        
        for (int r = 0; r < n; r++) {
            int brokenMask = 0;
            for (int c = 0; c < m; c++) {
                if (seats[r][c] == '#') {
                    brokenMask |= (1 << c);
                }
            }
            brokenRows[r] = brokenMask;
        }
        
        return res(brokenRows, dp, 0, 0, m);
    }
}