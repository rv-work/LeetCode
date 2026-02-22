class Solution {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
    }

    TrieNode root = new TrieNode();

    void insert(String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (curr.children[idx] == null) {
                curr.children[idx] = new TrieNode();
            }
            curr = curr.children[idx];
        }
        curr.isEnd = true;
    }

    List<Integer> search(String s, int start) {
        List<Integer> res = new ArrayList<>();
        TrieNode curr = root;

        for (int i = start; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';

            if (curr.children[idx] == null) break;
            curr = curr.children[idx];

            if (curr.isEnd) res.add(i + 1); 
        }
        return res;
    }

    public boolean wordBreak(String s, List<String> wordDict) {

        for (String w : wordDict) insert(w);

        int n = s.length();
        boolean dp[] = new boolean[n + 1];
        dp[0] = true;

        for (int i = 0; i < n; i++) {
            if (!dp[i]) continue;

            List<Integer> nextPositions = search(s, i);
            for (int nxt : nextPositions) {
                dp[nxt] = true;
            }
        }

        return dp[n];
    }
}