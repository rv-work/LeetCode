
class Solution {
  public List<Integer> findAnagrams(String s, String p) {
    List<Integer> ans = new ArrayList<>();
    int n = s.length();
    int m = p.length();

    if (n < m)
      return ans;

    int[] freqP = new int[26];
    int[] freqS = new int[26];

    for (char ch : p.toCharArray()) {
      freqP[ch - 'a']++;
    }

    for (int i = 0; i < m; i++) {
      freqS[s.charAt(i) - 'a']++;
    }

    if (Arrays.equals(freqP, freqS))
      ans.add(0);

    int i = 0;
    for (int j = m; j < n; j++) {
      freqS[s.charAt(j) - 'a']++;
      freqS[s.charAt(i) - 'a']--;
      i++;

      if (Arrays.equals(freqP, freqS)) {
        ans.add(i);
      }
    }

    return ans;
  }
}








class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if (s.length() < p.length()) return ans;

        int[] freq = new int[26];

        for (char c : p.toCharArray()) freq[c - 'a']++;

        int left = 0, right = 0, count = p.length();

        while (right < s.length()) {

            char rc = s.charAt(right);
            if (freq[rc - 'a'] > 0) count--;
            freq[rc - 'a']--;
            right++;

            if (count == 0) ans.add(left);

            if (right - left == p.length()) {
                char lc = s.charAt(left);
                if (freq[lc - 'a'] >= 0) count++;
                freq[lc - 'a']++;
                left++;
            }
        }

        return ans;
    }
}






