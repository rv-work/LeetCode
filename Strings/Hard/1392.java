class Solution {
    public String longestPrefix(String s) {
        int n = s.length();
        if (n <= 1) return ""; // 1 length ki string ka proper prefix nahi hota

        int[] lps = new int[n];
        int len = 0; // Pichle longest prefix-suffix ki length track karega
        int i = 1;   // Index 1 se check karna shuru karenge

        // LPS array build karne ka standard KMP loop
        while (i < n) {
            if (s.charAt(i) == s.charAt(len)) {
                // Characters match ho gaye! Length badhao aur aage badho
                len++;
                lps[i] = len;
                i++;
            } else {
                // Characters match nahi hue
                if (len != 0) {
                    // TRICKY PART: Agar mismatch hua aur hum mid-way hain, 
                    // toh hum zero par nahi jayenge, balki pichle valid prefix par jump karenge.
                    len = lps[len - 1]; 
                } else {
                    // Agar len 0 ho gaya, matlab ab koi match possible nahi hai is index par
                    lps[i] = 0;
                    i++;
                }
            }
        }

        // lps[n-1] humein poori string ke longest proper prefix-suffix ki length dega
        int longestLen = lps[n - 1];
        
        // Utni length ki substring return kardo (0 se start karke)
        return s.substring(0, longestLen);
    }
}