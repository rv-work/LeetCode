class Solution {
    public int minOperations(String s) {
        int even = 0; // 010101010...........
        int odd = 0; //  101010101..........

        for(int i = 0; i<s.length();i++){
            if(i % 2 == 0 && s.charAt(i) != '0')even++;
            if(i % 2 == 1 && s.charAt(i) != '1')even++;

            if(i % 2 == 0 && s.charAt(i) != '1')odd++;
            if(i % 2 == 1 && s.charAt(i) != '0')odd++;
        }

        return Math.min(odd , even);
    }
}