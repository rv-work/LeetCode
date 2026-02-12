class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        int maxLen = 0;

        // Try all substrings
        for (int i = 0; i < n; i++) {
            int[] freq = new int[26];
            for (int j = i; j < n; j++) {

                // Count frequencies for substring s[i...j]
                freq[s.charAt(j) - 'a']++;

                // Check if balanced
                int count = 0;
                boolean balanced = true;

                for (int f : freq) {
                    if (f > 0) {
                        if (count == 0) {
                            count = f;
                        } else if (count != f) {
                            balanced = false;
                            break;
                        }
                    }
                }

                if (balanced) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }
}
