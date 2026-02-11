class Solution {

    boolean res(int i, int j, String s, Set<String> set, List<String> ans, StringBuilder str) {
        if (i >= s.length()) {
            ans.add(str.toString().trim());
            return true;
        }

        if (j >= s.length()) return false;

        String sub = s.substring(i, j + 1);

        boolean ok = false;

        if (set.contains(sub)) {
            int prevLen = str.length();

            // add space if needed
            if (prevLen > 0) str.append(" ");
            str.append(sub);

            // explore next part
            if (res(j + 1, j + 1, s, set, ans, str)) {
                ok = true;
            }

            // backtrack
            str.setLength(prevLen);
        }

        // try extending current substring
        if (res(i, j + 1, s, set, ans, str)) ok = true;

        return ok;
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        List<String> ans = new ArrayList<>();
        res(0, 0, s, set, ans, new StringBuilder());
        return ans;
    }
}












class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        Map<Integer, List<String>> memo = new HashMap<>();
        return dfs(0, s, dict, memo);
    }

    private List<String> dfs(int index, String s, Set<String> dict,
                             Map<Integer, List<String>> memo) {

        if (memo.containsKey(index)) return memo.get(index);

        List<String> result = new ArrayList<>();

        if (index == s.length()) {
            result.add(""); // empty string to allow joining
            return result;
        }

        for (int j = index + 1; j <= s.length(); j++) {
            String sub = s.substring(index, j);

            if (dict.contains(sub)) {
                List<String> rest = dfs(j, s, dict, memo);

                for (String r : rest) {
                    if (r.isEmpty()) result.add(sub);
                    else result.add(sub + " " + r);
                }
            }
        }

        memo.put(index, result);
        return result;
    }
}
