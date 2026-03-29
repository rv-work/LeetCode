class Solution {
    public boolean canBeEqual(String s1, String s2) {
        int freq[] = new int[26];
        int n = s1.length();
        for(int i = 0; i<n; i++){
            freq[s1.charAt(i)-'a']++;
        }

        for(int i = 0; i<n; i++){
            freq[s2.charAt(i)-'a']--;
        }

        for(int i = 0; i<n; i++){
            if(freq[i] != 0) return false;
        }

        for(int i = 0; i<n; i++){
            if(s1.charAt(i) != s2.charAt(i) && s1.charAt(i) != s2.charAt((i+2)%n)) return false;
        }

        return true;
    }
}