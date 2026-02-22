class Solution {

    class TrieNode {
        TrieNode[] child = new TrieNode[2];
    }

    TrieNode root = new TrieNode();

    // Insert a number in bitwise trie
    void insert(int num) {
        TrieNode curr = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (curr.child[bit] == null) {
                curr.child[bit] = new TrieNode();
            }
            curr = curr.child[bit];
        }
    }

    // Find max XOR of num with existing numbers in trie
    int getMaxXor(int num) {
        TrieNode curr = root;
        int ans = 0;

        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            int opp = bit ^ 1;  // try opposite bit to maximize xor

            if (curr.child[opp] != null) {
                ans |= (1 << i);  // this bit contributes to XOR
                curr = curr.child[opp];
            } else {
                curr = curr.child[bit];
            }
        }
        return ans;
    }

    public int findMaximumXOR(int[] nums) {
        int maxXor = 0;

        // Insert first number
        insert(nums[0]);

        // For every next number: find best XOR then insert it
        for (int i = 1; i < nums.length; i++) {
            maxXor = Math.max(maxXor, getMaxXor(nums[i]));
            insert(nums[i]);
        }

        return maxXor;
    }
}