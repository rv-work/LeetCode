
class Solution {
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        int n = security.length;
        List<Integer> ans = new ArrayList<>();
        
        // Base case: agar total din hi window size (2*time + 1) se kam hain
        if (n <= 2 * time) return ans;
        
        int[] left = new int[n];
        int[] right = new int[n];
        
        // Step 1: Left array bharo (Count consecutive non-increasing days from left)
        for (int i = 1; i < n; i++) {
            if (security[i] <= security[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 0; // Chain toot gayi
            }
        }
        
        // Step 2: Right array bharo (Count consecutive non-decreasing days from right)
        for (int i = n - 2; i >= 0; i--) {
            if (security[i] <= security[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 0; // Chain toot gayi
            }
        }
        
        // Step 3: Check karo ki kahan par dono conditions satisfy ho rahi hain
        // Hum time se lekar n - time - 1 tak hi check karenge 
        // (taaki left aur right me enough din hon)
        for (int i = time; i < n - time; i++) {
            if (left[i] >= time && right[i] >= time) {
                ans.add(i);
            }
        }
        
        return ans;
    }
}