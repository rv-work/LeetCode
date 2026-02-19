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



// not n^2 kyunki ...........
// This is a Trade-off:

// If Transitions are many (the Outer loop runs frequently), then the Groups will be small (the Inner loop runs very few times).

// If Groups are large (the Inner loop runs many times), then the Transitions will be few (the Outer loop runs very few times).

// You cannot maximize both at the same time—or as the saying goes, "You can't have your cake and eat it too."








class Solution {
    public int countBinarySubstrings(String s) {
        int n = s.length();
        if (n <= 1) return 0;

        // Step 1: Har group ki length ko store karne ke liye list
        List<Integer> groups = new ArrayList<>();
        int currentGroupLen = 1;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                currentGroupLen++;
            } else {
                // Number badal gaya, purane group ki length add karo
                groups.add(currentGroupLen);
                currentGroupLen = 1;
            }
        }
        // Last group ko add karna mat bhulna
        groups.add(currentGroupLen);

        // Step 2: Padosi groups ka min nikal kar sum karo
        int count = 0;
        for (int i = 1; i < groups.size(); i++) {
            count += Math.min(groups.get(i - 1), groups.get(i));
        }

        return count;
    }
}