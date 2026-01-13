class Solution {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int ans = 0;
        Map<String , Integer> mp = new HashMap<>();
        Map<Character , Integer> st = new HashMap<>();
        int n = s.length();

        int l = 0; int r = 0;

        while(r < n){
            char ch = s.charAt(r);

            st.put(ch , st.getOrDefault(ch , 0) + 1);

            while(st.size() > maxLetters){
                char c = s.charAt(l++);
                st.put(c , st.get(c) -1);
                if(st.get(c) == 0) st.remove(c);
            }

            if(r-l+1 == minSize){
                String str = s.substring(l , r+1);

                mp.put(str ,  mp.getOrDefault(str , 0) + 1 );
                ans = Math.max(ans, mp.get(str));
                
                char c = s.charAt(l++);
                st.put(c , st.get(c) -1);
                if(st.get(c) == 0) st.remove(c);
            }

            r++;
        }


        return ans;

    }
}






