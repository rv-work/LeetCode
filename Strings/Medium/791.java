class Solution {
    public String customSortString(String order, String s) {

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), 0);
        }

        StringBuilder rem = new StringBuilder();  

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                rem.append(c);
            }
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < order.length(); i++) {
            char c = order.charAt(i);
            int count = map.get(c);

            while (count-- > 0) {
                ans.append(c);
            }
        }

        ans.append(rem.toString());

        return ans.toString();
    }
}








class Solution {
    public String customSortString(String order, String s) {
        // Count frequency of each character in s
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder ans = new StringBuilder();

        // First, add characters that appear in 'order'
        for (char c : order.toCharArray()) {
            while (freq[c - 'a'] > 0) {
                ans.append(c);
                freq[c - 'a']--;
            }
        }

        // Then, add remaining characters not present in order
        for (char c = 'a'; c <= 'z'; c++) {
            while (freq[c - 'a'] > 0) {
                ans.append(c);
                freq[c - 'a']--;
            }
        }

        return ans.toString();
    }
}