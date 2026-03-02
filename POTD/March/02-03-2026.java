class Solution {
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] zeros = new int[n];
        
        // Step 1: Har row ke trailing zeros count karke store kar lo
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 0) count++;
                else break; // Jaise hi 1 mile, zero count karna band kar do
            }
            zeros[i] = count;
        }
        
        int swaps = 0;
        
        // Step 2: Har row ko uski sahi jagah (valid condition) par set karo
        for (int i = 0; i < n; i++) {
            int reqZeros = n - 1 - i; // Row 'i' ko itne zeros chahiye
            
            int j = i;
            // Aage (neeche) aisi pehli row dhoondho jisme required zeros hon
            while (j < n && zeros[j] < reqZeros) {
                j++;
            }
            
            // Agar array khatam ho gaya par aisi koi row nahi mili
            if (j == n) return -1;
            
            // Step 3: Swapping aur Shifting
            swaps += (j - i); // Upar laane me (j-i) swaps lagenge
            
            // Jis row ko upar laye hain, uska zero count safely rakh lo
            int currentZeroCount = zeros[j];
            
            // Beech ki saari rows ko ek-ek step neeche shift kar do
            for (int k = j; k > i; k--) {
                zeros[k] = zeros[k - 1];
            }
            
            // Upar layi gayi row ko i-th position par set kar do
            zeros[i] = currentZeroCount;
        }
        
        return swaps;
    }
}