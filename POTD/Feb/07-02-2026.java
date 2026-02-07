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