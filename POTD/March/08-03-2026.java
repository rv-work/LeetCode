class Solution {
  public String findDifferentBinaryString(String[] nums) {
    int n = nums.length;
    Set<String> set = new HashSet<>();
    for (String str : nums) {
      set.add(str);
    }

    int max = (1 << n);

    for (int i = 0; i < max; i++) {
      StringBuilder str = new StringBuilder();
      int num = i;
      for (int j = 0; j < n; j++) {
        num >>= j;
        str.append((num & 1));
      }
      if (!set.contains(str.toString()))
        return str.toString();
    }

    return "";
  }
}

class Solution {
  public String findDifferentBinaryString(String[] nums) {
    int n = nums.length;

    Set<Integer> set = new HashSet<>();
    for (String s : nums)
      set.add(Integer.parseInt(s, 2));

    for (int i = 0; i < (1 << n); i++) {
      if (!set.contains(i)) {
        String ans = Integer.toBinaryString(i);
        while (ans.length() < n)
          ans = "0" + ans;
        return ans;
      }
    }
    return "";
  }
}

class Solution {

  public String findDifferentBinaryString(String[] nums) {
    Set<String> set = new HashSet<>();
    for (String s : nums)
      set.add(s);

    return solve("", nums.length, set);
  }

  String solve(String curr, int n, Set<String> set) {

    if (curr.length() == n) {
      if (!set.contains(curr))
        return curr;
      return null;
    }

    String left = solve(curr + "0", n, set);
    if (left != null)
      return left;

    return solve(curr + "1", n, set);
  }
}