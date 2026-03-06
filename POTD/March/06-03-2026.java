class Solution {
    public boolean checkOnesSegment(String s) {
        boolean have = false;

        for(int i = 1; i<s.length(); i++){
            if(s.charAt(i) == '1' && s.charAt(i-1) == '0'){
                if(have) return false;
                else have = true;
            }
        }

        return !have;
    }
}