
class Solution {

    void res(List<String> ans, StringBuilder s, int idx, String digits, String[] arr) {
        
        if (idx == digits.length()) {
            ans.add(s.toString());
            return;
        }

        int digitIdx = digits.charAt(idx) - '0' - 2;
        String letters = arr[digitIdx];

        for (int i = 0; i < letters.length(); i++) {
            
            s.append(letters.charAt(i));
            
            res(ans, s, idx + 1, digits, arr);
            
            s.deleteCharAt(s.length() - 1);  
        }
    }

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) return new ArrayList<>();

        String arr[] = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        List<String> ans = new ArrayList<>();
        
        res(ans, new StringBuilder(), 0, digits, arr);

        return ans;
    }
}