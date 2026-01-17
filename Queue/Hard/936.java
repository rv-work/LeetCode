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
        int n = target.length();
        int m = stamp.length();

        char[] S = stamp.toCharArray();
        char[] T = target.toCharArray();

        // For each window: which indices depend on it
        List<int[]> windows = new ArrayList<>();

        // Count of non-matching characters in each window
        int[] mismatch = new int[n - m + 1];

        // For each char in T: which windows include it
        List<List<Integer>> posToWindows = new ArrayList<>();

        for (int i = 0; i < n; i++) posToWindows.add(new ArrayList<>());

        // Build windows + mismatch count
        for (int i = 0; i <= n - m; i++) {
            int mis = 0;
            for (int j = 0; j < m; j++) {
                if (T[i + j] != S[j]) mis++;
            }
            mismatch[i] = mis;
            windows.add(new int[]{i, i + m - 1});

            // This window affects positions i...i+m-1
            for (int j = 0; j < m; j++) {
                posToWindows.get(i + j).add(i);
            }
        }

        boolean[] visitedWindow = new boolean[n - m + 1];
        boolean[] done = new boolean[n];

        Queue<Integer> q = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();

        // Add all windows that have ZERO mismatch → can stamp immediately
        for (int i = 0; i <= n - m; i++) {
            if (mismatch[i] == 0) {
                q.offer(i);
                visitedWindow[i] = true;
            }
        }

        // BFS
        while (!q.isEmpty()) {
            int win = q.poll();
            ans.add(win);

            int start = win;
            int end = win + m - 1;

            for (int pos = start; pos <= end; pos++) {
                if (done[pos]) continue;

                done[pos] = true;  // mark this char as '*'

                for (int w : posToWindows.get(pos)) {
                    if (visitedWindow[w]) continue;

                    mismatch[w]--;   // one required char is now '*'
                    if (mismatch[w] == 0) {
                        visitedWindow[w] = true;
                        q.offer(w);
                    }
                }
            }
        }

        // All chars must be '*'
        for (boolean c : done) {
            if (!c) return new int[0];
        }

        // Reverse answer (because BFS gives reverse stamping)
        Collections.reverse(ans);

        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) res[i] = ans.get(i);
        return res;
    }
}
