class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int [] ans = new int[n];

        Stack<Integer> st = new Stack<>();
        st.push(0);

        for(int i = 1; i<n; i++){

            int ele = temperatures[i];

            while(!st.isEmpty() && ele > temperatures[st.peek()]){
                ans[st.peek()] = i - st.pop();
            }
            st.push(i);
        }

        return ans;
    }
}