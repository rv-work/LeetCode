class Solution {
  public boolean checkStrings(String s1, String s2) {
    int n = s1.length();

    int even[] = new int[26];
    int odd[] = new int[26];

    for (int i = 0; i < n; i++) {
      char c = s1.charAt(i);
      if (i % 2 == 0) {
        even[c - 'a']++;
      } else {
        odd[c - 'a']++;
      }
    }

    for (int i = 0; i < n; i++) {
      char c = s2.charAt(i);
      if (i % 2 == 0) {
        even[c - 'a']--;
      } else {
        odd[c - 'a']--;
      }
    }

    for (int i = 0; i < even.length; i++) {
      int num1 = even[i];
      int num2 = odd[i];

      if (num1 != 0 || num2 != 0)
        return false;
    }

    return true;
  }
}