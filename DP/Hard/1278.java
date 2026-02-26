class Solution {
    
    // Ye batayega ki substring s[i...j] ko palindrome 
    // banne me kitne characters change karne padenge.
    int changes(String s, int i, int j) {
        int count = 0;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                count++;
            }
            i++;
            j--;
        }
        return count;
    }

    int res(int i, String s, int k, Integer[][] dp) {
        int n = s.length();
        
        // Base Case 1: Agar sirf 1 hi tukda (partition) bacha hai, 
        // to humein poori bachi hui string ek sath leni padegi.
        if (k == 1) {
            return changes(s, i, n - 1);
        }
        
        // Base Case 2: Agar bache hue characters aur bache hue partitions barabar hain,
        // (Jaise 3 char bache hain aur 3 hi tukde karne hain). 
        // To har character apne aap me ek length-1 palindrome hoga. Cost = 0.
        if (n - i == k) {
            return 0;
        }
        
        // Memoization Check
        if (dp[i][k] != null) return dp[i][k];
        
        int minAns = 10000; // Infinity ke liye bada number (overflow se bachne ke liye Integer.MAX_VALUE nahi liya)

        // Loop kahan tak chalega?
        // Hum j ko 'n - k' tak hi le jayenge, kyunki aage ke (k-1) partitions 
        // ke liye kam se kam (k-1) characters bachne zaroori hain!
        for (int j = i; j <= n - k; j++) {
            
            // 1. i se j tak ek tukda kaata, uski cost nikali
            int currentCost = changes(s, i, j);
            
            // 2. Bachi hui string (j+1 se end) me (k-1) tukde karne ko kaha
            int nextCost = res(j + 1, s, k - 1, dp);
            
            // 3. Minimum update kiya
            minAns = Math.min(minAns, currentCost + nextCost);
        }

        return dp[i][k] = minAns;
    }
    
    public int palindromePartition(String s, int k) {
        int n = s.length();
        
        if (n == k) return 0;
        
        Integer[][] dp = new Integer[n][k + 1];
        
        return res(0, s, k, dp);
    }
}