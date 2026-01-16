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






class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> dq = new ArrayDeque<>();

        for (int a : asteroids) {
            boolean alive = true;

            while (!dq.isEmpty() && dq.peekLast() > 0 && a < 0) {
                if (dq.peekLast() < -a) {
                    dq.pollLast();
                } else if (dq.peekLast() == -a) {
                    dq.pollLast();
                    alive = false;
                    break;
                } else {
                    alive = false;
                    break;
                }
            }

            if (alive) dq.addLast(a);
        }

        int[] res = new int[dq.size()];
        int i = 0;
        for (int x : dq) res[i++] = x;
        return res;
    }
}








class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        List<Integer> list = new ArrayList<>();

        for (int a : asteroids) {
            boolean alive = true;

            while (!list.isEmpty() && list.get(list.size()-1) > 0 && a < 0) {
                int top = list.get(list.size()-1);

                if (top < -a) {
                    list.remove(list.size()-1);
                } else if (top == -a) {
                    list.remove(list.size()-1);
                    alive = false;
                    break;
                } else {
                    alive = false;
                    break;
                }
            }

            if (alive) list.add(a);
        }

        return list.stream().mapToInt(i -> i).toArray();
    }
}


// int[] arr = new int[list.size()];
// for (int i = 0; i < list.size(); i++) {
//     arr[i] = list.get(i);
// }
