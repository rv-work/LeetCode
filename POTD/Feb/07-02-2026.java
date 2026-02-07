class Solution {
    public int minimumDeletions(String s) {
        Stack<Character> st = new Stack<>();
        int cnt = 0;
        for(int i = 0; i<s.length(); i++){
            char ch = s.charAt(i);

            if(st.isEmpty()
                || (st.peek() == 'a' && ch == 'a') 
                || (st.peek() == 'b' && ch == 'b')  
                || (st.peek() == 'a' && ch == 'b')) st.push(ch);
             else {
                st.pop();
                cnt++;
            }
        }


        return cnt;
    }
}










class Solution {
    public int minimumDeletions(String s) {
        int n = s.length();

        int preB[] = new int[n];
        int suffA[] = new int[n];

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'b')
                preB[i]++;
            if (i > 0)
                preB[i] += preB[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == 'a')
                suffA[i]++;
            if (i < n - 1)
                suffA[i] += suffA[i + 1];
        }

        int ans = Integer.MAX_VALUE;

        for (int i = -1; i < n; i++) {
            int left = (i >= 0) ? preB[i] : 0;
            int right = (i + 1 < n) ? suffA[i + 1] : 0;
            ans = Math.min(ans, left + right);
        }

        return ans;
    }
}










class Solution {
    public int minimumDeletions(String s) {
        int n = s.length();

        int[] preB = new int[n];
        int[] suffA = new int[n];

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            preB[i] = cnt;
            if(s.charAt(i) == 'b') cnt++;
        }

        cnt = 0;
        for (int i = n - 1; i >= 0; i--) {
            suffA[i] = cnt;
             if(s.charAt(i) == 'a') cnt++;
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, preB[i] + suffA[i]);
        }

        return ans;
    }
}
