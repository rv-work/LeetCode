class Solution {
    public boolean hasAllCodes(String s, int k) {
        int n = s.length();
        int totalCodes = 1 << k; 
        
        if (n < totalCodes + k - 1) {
            return false;
        }
        
        boolean[] seen = new boolean[totalCodes];
        
        int hash = 0;
        int count = 0;
        
        // Ye mask extra left bits ko hatane ke kaam aayega (Jaise k=3 ke liye mask = ....0000111 in binary)
        int allOnesMask = totalCodes - 1; 
        
        for (int i = 0; i < n; i++) {
            // 1. Puraane hash ko left shift karo (<< 1)
            // 2. Naya bit add karo: (s.charAt(i) - '0')
            // 3. Mask lagao taaki sirf 'k' bits hi bachein: & allOnesMask
            hash = ((hash << 1) | (s.charAt(i) - '0')) & allOnesMask;
            
            if (i >= k - 1) {
                if (!seen[hash]) {
                    seen[hash] = true;
                    count++;
                    
                    if (count == totalCodes) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
}