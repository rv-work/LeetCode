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









