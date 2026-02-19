class Solution {
    public int countBinarySubstrings(String s) {
        int n = s.length();
        int count = 0;

        // Sirf ek loop chalayenge
        for (int i = 1; i < n; i++) {
            
            // Check: Kya yahan character badal raha hai?
            // Chahe 0->1 ho ya 1->0, dono chalenge.
            if (s.charAt(i) != s.charAt(i - 1)) {
                
                int left = i - 1;
                int right = i;
                
                // Hame ye yaad rakhna padega ki center par kya tha
                // Taaki expand karte waqt 'Left' wala wahi rahe aur 'Right' wala wahi rahe
                char leftChar = s.charAt(left);
                char rightChar = s.charAt(right);

                // Expand Logic
                while (left >= 0 && right < n && 
                       s.charAt(left) == leftChar && s.charAt(right) == rightChar) {
                    
                    count++;
                    left--;
                    right++;
                }
            }
        }
        
        return count;
    }
}