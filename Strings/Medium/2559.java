class Solution {
  boolean check(char c) {
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
  }

  public int[] vowelStrings(String[] words, int[][] queries) {
    int n = queries.length;
    int ans[] = new int[n];
    int[] pre = new int[words.length];

    for (int i = 0; i < words.length; i++) {
      String str = words[i];
      int len = str.length();
      if (check(str.charAt(0)) && check(str.charAt(len - 1)))
        pre[i] = 1;

      if (i > 0)
        pre[i] += pre[i - 1];
    }

    for (int i = 0; i < n; i++) {
      int q[] = queries[i];
      int l = q[0];
      int r = q[1];
      ans[i] = pre[r];
      if (l > 0)
        ans[i] -= pre[l - 1];
    }

    return ans;
  }
}