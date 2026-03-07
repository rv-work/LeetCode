class Solution {
    public int minFlips(String s) {
        int n = s.length();
        int ans = Integer.MAX_VALUE;
        
        int odd = 0;  // Tracks flips needed for pattern 101010...
        int even = 0; // Tracks flips needed for pattern 010101...

        int l = 0; 
        int r = 0;
        
        while (r < 2 * n) {
            char c = s.charAt(r % n);

            if (r % 2 == 0) {
                if (c != '0') even++; 
                if (c != '1') odd++;  
            } else {
                if (c != '1') even++; 
                if (c != '0') odd++;  
            }

            if (r - l + 1 > n) {
                char leftChar = s.charAt(l % n);
                if (l % 2 == 0) {
                    if (leftChar != '0') even--;
                    if (leftChar != '1') odd--;
                } else {
                    if (leftChar != '1') even--;
                    if (leftChar != '0') odd--;
                }
                l++;
            }

            if (r - l + 1 == n) {
                ans = Math.min(ans, Math.min(even, odd));
            }

            r++; 
        }
        
        return ans;
    }
}

// yha even .. odd... swap hote hain hr bar window size bnne ke bad ...... ( just count ke roles)
// phle even jo 0101 kr rha tha ab vo 1010 kr rha hota hai and vice-verse....