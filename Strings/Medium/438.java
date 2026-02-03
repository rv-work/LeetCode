
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





