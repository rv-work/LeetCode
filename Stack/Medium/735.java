class Solution {
    public int[] asteroidCollision(int[] asteroids) {

        Stack<Integer> st = new Stack<>();

        for (int a : asteroids) {

            // we need to check for collisions only when:
            // top > 0 (right) and a < 0 (left)
            boolean alive = true;

            while (!st.isEmpty() && st.peek() > 0 && a < 0) {

                if (st.peek() < -a) {
                    // top is smaller → top breaks
                    st.pop();
                    continue;
                }
                else if (st.peek() == -a) {
                    // both equal → both break
                    st.pop();
                }

                // in both equal or top bigger → new 'a' dies
                alive = false;
                break;
            }

            if (alive) st.push(a);
        }

        // convert to array
        int[] ans = new int[st.size()];
        for (int i = st.size() - 1; i >= 0; i--)
            ans[i] = st.pop();

        return ans;
    }
}





