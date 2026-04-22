

class Solution {
    
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
    }
    
    TrieNode root = new TrieNode();
    
    private void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
    }
    
    private boolean search(TrieNode node, String query, int idx, int editsLeft) {
        if (editsLeft < 0) return false; 
        if (idx == query.length()) return true; 
        
        char targetChar = query.charAt(idx);
        
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                int cost = (targetChar - 'a' == i) ? 0 : 1;
                
                if (search(node.children[i], query, idx + 1, editsLeft - cost)) {
                    return true;
                }
            }
        }
        
        return false;
    }

    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        for (String word : dictionary) {
            insert(word);
        }
        
        List<String> result = new ArrayList<>();
        
        for (String query : queries) {
            if (search(root, query, 0, 2)) {
                result.add(query);
            }
        }
        
        return result;
    }
}