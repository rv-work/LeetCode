class Solution {
    public int romanToInt(String s) {
        int[] map = new int[26];
        map['I' - 'A'] = 1;
        map['V' - 'A'] = 5;
        map['X' - 'A'] = 10;
        map['L' - 'A'] = 50;
        map['C' - 'A'] = 100;
        map['D' - 'A'] = 500;
        map['M' - 'A'] = 1000;

        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            int curr = map[s.charAt(i) - 'A'];

            if (i + 1 < s.length() && curr < map[s.charAt(i + 1) - 'A']) {
                ans -= curr;
            } else {
                ans += curr;
            }

            // why we are not simpli adding curr 
            // see .. VI is ok add 5 then add 1 but IV ... is 4 not add 1 then 5 ... so we need to dec 1 then add 5 to balance thats why 
        }

        return ans;
    }
}