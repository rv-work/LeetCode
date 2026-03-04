class Solution {
    public int numSpecial(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            int onesInRow = 0;
            int colIndex = -1;

            // Step 1: Current row 'i' me kitne 1 hain count karo
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1) {
                    onesInRow++;
                    colIndex = j; // Is 1 ka column number yaad rakh lo
                }
            }

            // Step 2: Agar row me exactly ek hi 1 hai, tabhi aage check karenge
            if (onesInRow == 1) {
                int onesInCol = 0;
                
                // Step 3: Us column me upar se neeche tak 1s count karo
                for (int r = 0; r < n; r++) {
                    if (mat[r][colIndex] == 1) {
                        onesInCol++;
                    }
                }

                // Step 4: Agar column me bhi exactly ek hi 1 nikla, toh ye special hai!
                if (onesInCol == 1) {
                    ans++;
                }
            }
        }

        return ans;
    }
}