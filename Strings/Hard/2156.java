class Solution {
    public String subStrHash(String s, int power, int modulo, int k, int hashValue) {
        long p = power;
        long m = modulo;
        int n = s.length();
        
        long currentHash = 0;
        long pk = 1; // Ye p^k calculate karne ke liye hai
        
        // Step 1: Calculate p^k % modulo (Jo character remove hoga uske multiplier ke liye)
        for (int i = 0; i < k; i++) {
            pk = (pk * p) % m;
        }
        
        // Step 2: Sabse aakhiri window (Rightmost) ka Hash nikal lo
        long p_pow = 1;
        for (int i = n - k; i < n; i++) {
            long val = s.charAt(i) - 'a' + 1;
            currentHash = (currentHash + val * p_pow) % m;
            p_pow = (p_pow * p) % m;
        }
        
        int bestIndex = -1;
        // Agar aakhiri window hi answer hai
        if (currentHash == hashValue) {
            bestIndex = n - k;
        }
        
        // Step 3: Sliding Window from Right to Left! (The Masterstroke)
        for (int i = n - k - 1; i >= 0; i--) {
            long val_in = s.charAt(i) - 'a' + 1;       // Naya character jo window me aaya
            long val_out = s.charAt(i + k) - 'a' + 1;  // Purana character jo window se nikla
            
            // Rolling Hash Formula
            currentHash = (currentHash * p) % m;
            currentHash = (currentHash + val_in) % m;
            
            // Subtraction with Modulo (Negative number se bachne ke liye + m kiya hai)
            long sub = (val_out * pk) % m;
            currentHash = (currentHash - sub + m) % m;
            
            // Hum right se left aa rahe hain, toh aakhiri baar (leftmost) 
            // jab match milega wahi humara 'first substring' hoga
            if (currentHash == hashValue) {
                bestIndex = i;
            }
        }
        
        // Substring return kar do
        return s.substring(bestIndex, bestIndex + k);
    }
}