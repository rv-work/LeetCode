class Solution {
    int[] bit;

    int OFFSET = 40000; 
    
    // BIT ka size offset + max possible positive diff se bada hona chahiye
    // Max diff = 20000, max target = 20000 + 10000 = 30000.
    // 30000 + 40000 (offset) = 70000. 100000 is perfectly safe.
    int MAX_VAL = 100000; 

     // BIT: Point Update (index par val add karo)
    void update(int index, int val) {
        while (index < MAX_VAL) {
            bit[index] += val;
            index += (index & -index);
        }
    }

    // BIT: Prefix Sum (1 se lekar index tak ka sum)
    int query(int index) {
        int sum = 0;
        while (index > 0) {
            sum += bit[index];
            index -= (index & -index);
        }
        return sum;
    }

    public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        bit = new int[MAX_VAL];
        long totalPairs = 0; 
        int n = nums1.length;

        for (int j = 0; j < n; j++) {
            int c_j = nums1[j] - nums2[j];
            int target = c_j + diff;

            // 1. Pehle count karo ki array me abhi tak kitne elements <= target aaye hain
            totalPairs += query(target + OFFSET);

            // 2. Fir current element c_j ko BIT me add kar do
            update(c_j + OFFSET, 1);
        }

        return totalPairs;
    }

   
}