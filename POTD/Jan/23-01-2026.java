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
        if (a.sum != b.sum) return Long.compare(a.sum, b.sum);
        return Integer.compare(a.node.idx, b.node.idx);
    };

    public int minimumPairRemoval(int[] nums) {

        int n = nums.length;
// DLL bna re
        Node head = new Node(nums[0], 0);
        Node temp = head;
        for (int i = 1; i < n; i++) {
            Node nn = new Node(nums[i], i);
            temp.next = nn;
            nn.prev = temp;
            temp = nn;
        }


// sare pairs daal rhe second last tak 
        TreeSet<Pair> set = new TreeSet<>(comp);
        Node cur = head;
        while (cur != null && cur.next != null) {
            Pair p = new Pair(cur.data + cur.next.data, cur);
            set.add(p);
            cur = cur.next;
        }


// count initial bad pairs
        int bad = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) bad++;
        }


        int ans = 0;

// jab tak bad pairs bacte hain
        while (bad > 0) {

            Pair best = set.pollFirst(); // get minimum pair sum

            Node me = best.node; // curr node
            Node prevToMe = me.prev; // prev node
            Node nextToMe = me.next; // next node


//check is y minimum pair sum ... bad tha kya .. if yes then 1 km kr do 
            boolean wasBadAB = (nextToMe.data < me.data);
            if (wasBadAB) bad--;


// kyunki mai ht chuka hu to mere pichhe vala mere se sum kiya tha isiliye use bhi htao bad me nya sum ke sath daal denege......
            if (prevToMe != null) {
                Pair p = new Pair(prevToMe.data + me.data  , prevToMe);
                set.remove(p);
            }

// kyunki mai aur mere aage vala ab ek hone vale hain aur mere jagah aane vale hain to .. aage vale ko hta denge......         
            if (nextToMe.next != null) {
                Pair p = new Pair(nextToMe.data + nextToMe.next.data  , nextToMe);
                set.remove(p);
            }


// purani values .. ye kaam aayengi check krne ke liye ki . bad pairs the ya nhi ..
            long oldMeData = me.data;
            long oldNextToMeData = nextToMe.data;



// merge mujhe aur mere aage valo ko .....
            me.data = oldMeData + oldNextToMeData;
            me.idx = Math.min(me.idx, nextToMe.idx); // optional hai ... jaruri nhi
            me.next = nextToMe.next;
            if (nextToMe.next != null) nextToMe.next.prev = me;


// Bad count updation.................. before insert
            // check ki piche vala aur mera relation phle bad/good & abhi nye me bad/good
            if (prevToMe != null) {
                long prevVal = prevToMe.data;

                boolean was = (prevVal > oldMeData);
                boolean now = (prevVal > me.data);

                if (was && !now) bad--;
                else if (!was && now) bad++;
            }

            // check ki aage vala aur uske aage vala relation phle bad/good & abhi nye me bad/good
            if (nextToMe.next != null) {
                long nextVal = nextToMe.next.data;

                boolean was = (oldNextToMeData > nextVal);
                boolean now = (me.data > nextVal);

                if (was && !now) bad--;
                else if (!was && now) bad++;
            }

// INSERT NEW PAIRS

             // mujhse phle vala jo ab meri nyi value lega
            if (prevToMe != null) {
                Pair left = new Pair(prevToMe.data + me.data, prevToMe);
                set.add(left);
            }

 
           // mai khud jo ab meri nyi value lega 
// yha nextToMe nhi use krna hai kyunki vo purana hai aur yha m nya vale next ko lekar add kr rhe
            if (me.next != null) {
                Pair right = new Pair(me.data + me.next.data, me);
                set.add(right);
            }


            ans++;
        }

        return ans;
    }
}
