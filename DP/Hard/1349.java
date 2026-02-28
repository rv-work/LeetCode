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