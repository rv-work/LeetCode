
class Solution {
    public String rankTeams(String[] votes) {
        int n = votes[0].length();
        
        // Step 1: Har team ke har position ke votes count karne ke liye 2D array
        // count[team][position]
        int[][] count = new int[26][n];
        
        for (String vote : votes) {
            for (int i = 0; i < n; i++) {
                char team = vote.charAt(i);
                count[team - 'A'][i]++;
            }
        }
        
        // Step 2: Jo teams participate kar rahi hain, unhe ek array me daalo
        Character[] teams = new Character[n];
        for (int i = 0; i < n; i++) {
            teams[i] = votes[0].charAt(i);
        }
        
        // Step 3: Custom Sorting (Tumhara logic yahan apply hoga)
        Arrays.sort(teams, (a, b) -> {
            // Har position ke votes compare karo (0 se lekar n-1 tak)
            for (int i = 0; i < n; i++) {
                if (count[a - 'A'][i] != count[b - 'A'][i]) {
                    // Jiske votes jyada hain, use pehle rakho (Descending order)
                    return count[b - 'A'][i] - count[a - 'A'][i];
                }
            }
            // Agar saare votes barabar hain, to Alphabetically sort karo (Ascending order)
            return a - b;
        });
        
        // Step 4: Sorted teams ko StringBuilder me jod do
        StringBuilder ans = new StringBuilder();
        for (char c : teams) {
            ans.append(c);
        }
        
        return ans.toString();
    }
}