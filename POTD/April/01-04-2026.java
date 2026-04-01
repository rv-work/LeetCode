class Solution {
  public List<Integer> survivedRobotsHealths(int[] p, int[] h, String d) {
    int n = p.length;
    int robot[][] = new int[n][4];
    for (int i = 0; i < n; i++) {
      int dir = d.charAt(i) == 'L' ? 0 : 1;
      robot[i][0] = i;
      robot[i][1] = p[i];
      robot[i][2] = h[i];
      robot[i][3] = dir;
    }

    Arrays.sort(robot, (a, b) -> a[1] - b[1]);
    Stack<Integer> st = new Stack<>();

    for (int i = 0; i < n; i++) {
      while (!st.isEmpty() && robot[st.peek()][3] == 1 && robot[i][3] == 0) {
        int top = st.peek();

        if (robot[top][2] == robot[i][2]) {
          st.pop();
          robot[i][2] = 0;
          break;
        } else if (robot[top][2] > robot[i][2]) {
          robot[top][2] -= 1;
          robot[i][2] = 0;
          break;
        } else {
          robot[i][2] -= 1;
          st.pop();
        }
      }

      if (robot[i][2] > 0) {
        st.push(i);
      }

    }

    int res[][] = new int[st.size()][2];
    int k = 0;
    while (!st.isEmpty()) {
      int i = st.pop();
      res[k][0] = robot[i][0];
      res[k][1] = robot[i][2];
      k++;
    }

    Arrays.sort(res, (a, b) -> a[0] - b[0]);

    List<Integer> ans = new ArrayList<>();
    for (int i = 0; i < res.length; i++) {
      ans.add(res[i][1]);
    }

    return ans;

  }
}