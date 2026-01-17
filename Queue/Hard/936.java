class Solution {

    int getIndex(String stamp, char[] arr) {
        int n = arr.length;
        int m = stamp.length();

        int idx = -1;
        int max = 0;

        for (int i = 0; i <= n - m; i++) {
            int j = i;
            boolean notStar = false;

            while (j < i + m && (arr[j] == '*' || arr[j] == stamp.charAt(j - i))) {
                if (arr[j] != '*') notStar = true;
                j++;
            }

            if (j == i + m && notStar && m > max) {
                max = m;
                idx = i;
            }
        }
        return idx;
    }

    public int[] movesToStamp(String stamp, String target) {
        int n = target.length();
        char[] arr = target.toCharArray();
        List<Integer> ans = new ArrayList<>();

        while (true) {
            int idx = getIndex(stamp, arr);

            if (idx == -1) break;

            for (int k = idx; k < idx + stamp.length(); k++) {
                arr[k] = '*';
            }

            ans.add(idx);
        }

        for (char c : arr) {
            if (c != '*') return new int[0];
        }

        Collections.reverse(ans);

       int[] res = new int[ans.size()];
       int idx = 0;
       for (int x : ans) {
           res[idx++] = x;
       }


        return res;
    }
}



// actually is qiestion me hm ulta game play kr rhe hain

// see what we are doing is ki ...find max possible lenth each time kyunki unhe override krne me problem nhi vo to kr hi skte so reverse game play hai .... matlab last steo hm phle calculate kr rhe ...


//  target:  a b a b a b c b c b a b a b c b c


// layer 1:          a b c           a b c    
// layer 2:      a b c   a b c   a b c   a b c
// layer 3:  a b c           a b c



//  pass 1:  a b a b a b c b c b a b a b c b c
//                   ^ ^ ^           ^ ^ ^

//  pass 2:  a b a b * * * b c b a b * * * b c
//               ^ ^ ^   ^ ^ ^   ^ ^ ^   ^ ^ ^

//  pass 3:  a b * * * * * * * b * * * * * * *
//           ^ ^ ^           ^ ^ ^

//  pass 4:  * * * * * * * * * * * * * * * * *






// NOTE : Assume Like Topological Sort!!!









class Solution {
    public int[] movesToStamp(String stamp, String target) {
        int m = stamp.length(), n = target.length();
        char[] S = stamp.toCharArray();
        char[] T = target.toCharArray();

        // Each window info: which chars need to match, which need to star
        List<Window> windows = new ArrayList<>();

        // For each T index, which windows include it
        List<List<Integer>> posToWindows = new ArrayList<>();
        for (int i = 0; i < n; i++) posToWindows.add(new ArrayList<>());

        // Build windows
        for (int i = 0; i <= n - m; i++) {
            List<Integer> notStar = new ArrayList<>();
            List<Integer> matched = new ArrayList<>();

            for (int j = 0; j < m; j++) {
                if (T[i + j] == S[j]) matched.add(i + j);
                else notStar.add(i + j);
                posToWindows.get(i + j).add(i);
            }
            windows.add(new Window(notStar, matched));
        }

        boolean[] done = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();

        // Add windows that already match fully
        for (int i = 0; i <= n - m; i++) {
            if (windows.get(i).need.size() == 0) q.offer(i);
        }

        // BFS
        while (!q.isEmpty()) {
            int i = q.poll();
            ans.add(i);

            // Stamp this window → mark its chars as '*'
            for (int pos : windows.get(i).match) {
                if (done[pos]) continue;

                done[pos] = true;

                // Affected windows: reduce mismatch count
                for (int w : posToWindows.get(pos)) {
                    Window win = windows.get(w);
                    win.need.remove(Integer.valueOf(pos));
                    if (win.need.size() == 0) q.offer(w);
                }
            }
        }

        // Check if everything became '*'
        for (boolean x : done) if (!x) return new int[0];

        // reverse answer
        Collections.reverse(ans);

        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) res[i] = ans.get(i);

        return res;
    }

    static class Window {
        List<Integer> need;   // positions that still mismatch
        List<Integer> match;  // positions that match
        Window(List<Integer> need, List<Integer> match) {
            this.need = need;
            this.match = match;
        }
    }
}
