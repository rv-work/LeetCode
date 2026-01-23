class Solution {

  class Node {
    long data;
    int idx;
    Node next, prev;

    Node(long data, int idx) {
      this.data = data;
      this.idx = idx;
    }
  }

  class Pair {
    long sum;
    Node node;

    Pair(long sum, Node node) {
      this.sum = sum;
      this.node = node;
    }
  }

  Comparator<Pair> comp = (a, b) -> {
    if (a.sum != b.sum)
      return Long.compare(a.sum, b.sum);
    return Integer.compare(a.node.idx, b.node.idx);
  };

  public int minimumPairRemoval(int[] nums) {

    int n = nums.length;

    // Build DLL
    Node head = new Node(nums[0], 0);
    Node temp = head;
    for (int i = 1; i < n; i++) {
      Node nn = new Node(nums[i], i);
      temp.next = nn;
      nn.prev = temp;
      temp = nn;
    }

    TreeSet<Pair> set = new TreeSet<>(comp);
    HashMap<Node, Pair> map = new HashMap<>();

    // Initialize pairs
    Node cur = head;
    while (cur != null && cur.next != null) {
      Pair p = new Pair(cur.data + cur.next.data, cur);
      set.add(p);
      map.put(cur, p);
      cur = cur.next;
    }

    int bad = 0;
    for (int i = 1; i < n; i++) {
      if (nums[i] < nums[i - 1])
        bad++;
    }

    int ans = 0;

    while (bad > 0) {

      Pair best = set.pollFirst();
      Node a = best.node;
      Node b = a.next;

      map.remove(a);

      // ========= BEFORE MERGE: handle a > b =========
      boolean wasBadAB = (b.data < a.data);
      if (wasBadAB)
        bad--;

      // REMOVE AFFECTED PAIRS
      if (a.prev != null) {
        Pair p = map.remove(a.prev);
        if (p != null)
          set.remove(p);
      }
      Pair p2 = map.remove(a);
      if (p2 != null)
        set.remove(p2);

      if (b != null) {
        Pair p3 = map.remove(b);
        if (p3 != null)
          set.remove(p3);
      }

      // ========= MERGE =========
      long oldA = a.data;
      long oldB = b.data;

      a.data = a.data + b.data;
      a.idx = Math.min(a.idx, b.idx); // keep leftmost index

      // remove b from DLL
      a.next = b.next;
      if (b.next != null)
        b.next.prev = a;

      // ========= AFTER MERGE — LEFT SIDE =========
      if (a.prev != null) {

        long prevVal = a.prev.data;

        boolean wasBadLeft = (prevVal > oldA);
        boolean nowBadLeft = (prevVal > a.data);

        if (wasBadLeft && !nowBadLeft)
          bad--;
        else if (!wasBadLeft && nowBadLeft)
          bad++;
      }

      // ========= AFTER MERGE — RIGHT SIDE =========
      if (a.next != null) {

        long nextVal = a.next.data;

        boolean wasBadRight = (oldB > nextVal);
        boolean nowBadRight = (a.data > nextVal);

        if (wasBadRight && !nowBadRight)
          bad--;
        else if (!wasBadRight && nowBadRight)
          bad++;
      }

      // RE-INSERT NEW PAIRS

      if (a.prev != null) {
        Pair left = new Pair(a.prev.data + a.data, a.prev);
        set.add(left);
        map.put(a.prev, left);
      }

      if (a.next != null) {
        Pair right = new Pair(a.data + a.next.data, a);
        set.add(right);
        map.put(a, right);
      }

      ans++;
    }

    return ans;
  }
}
