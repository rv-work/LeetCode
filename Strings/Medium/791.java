class Solution {
    public String customSortString(String order, String s) {

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), 0);
        }

        StringBuilder rem = new StringBuilder();  

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                rem.append(c);
            }
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < order.length(); i++) {
            char c = order.charAt(i);
            int count = map.get(c);

            while (count-- > 0) {
                ans.append(c);
            }
        }

        ans.append(rem.toString());

        return ans.toString();
    }
}