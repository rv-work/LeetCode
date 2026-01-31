class Solution {
    public int maximumXorProduct(long a, long b, int n) {
        long xXORa = 0;
        long xXORb = 0;
        long MOD = 1_000_000_007;

        // Iterate from the most significant bit down to 0
        for (long i = 49; i >= 0; i--) {
            long mask = 1L << i; // Use 1L to prevent overflow
            long bitA = (a & mask) > 0 ? 1 : 0;
            long bitB = (b & mask) > 0 ? 1 : 0;

            if (i >= n) {
                // For bits >= n, we cannot change them. 
                // They just inherit values from a and b.
                if (bitA == 1) xXORa |= mask;
                if (bitB == 1) xXORb |= mask;
            } else {
                // For bits < n, we can influence the result.
                
                // Case 1: Bits match (0,0 or 1,1).
                // Strategy: Make both 1 to maximize value.
                if (bitA == bitB) {
                    xXORa |= mask;
                    xXORb |= mask;
                } 
                // Case 2: Bits differ (0,1 or 1,0).
                // Strategy: Give the '1' to the smaller number to balance them.
                else {
                    if (xXORa > xXORb) {
                        xXORb |= mask; // Give 1 to the smaller one (b)
                        // xXORa gets 0 implicitly (do nothing)
                    } else {
                        xXORa |= mask; // Give 1 to the smaller one (a)
                    }
                }
            }
        }

        // Apply Modulo at the very end
        xXORa %= MOD;
        xXORb %= MOD;
        return (int) ((xXORa * xXORb) % MOD);
    }
}