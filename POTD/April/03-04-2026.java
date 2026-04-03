
class Solution {
    int[] L, R, pos;
    Integer[][] dp;
    int n;

    int getW(int l, int r, int[] cnt, Map<Integer, Integer> map) {
        if (l > r) return 0;
        int idxL = map.get(l);
        int idxR = map.get(r);
        if (idxL == 0) return cnt[idxR];
        return cnt[idxR] - cnt[idxL - 1];
    }

    private int rec(int i, int lastDir, int[] cnt, Map<Integer, Integer> map) {
        if (i == n) return 0; 

        if (dp[i][lastDir] != null) return dp[i][lastDir];

        int pos_i = pos[i];
        int left_i = L[i];
        int right_i = R[i];

        int pos_prev = (i > 0) ? pos[i - 1] : -1;
        int right_prev = (i > 0) ? R[i - 1] : -1;

        // ==========================================
        // OPTION 1: CURRENT ROBOT FIRES LEFT
        // ==========================================
        int added_left = getW(left_i, pos_i, cnt, map);
        
        if (lastDir == 0) {
            if (left_i == pos_prev) {
                added_left -= getW(pos_prev, pos_prev, cnt, map); // Single point overlap
            }
        } else if (lastDir == 1) { // Pichle ne RIGHT fire kiya tha (Aamne-Saamne takkar!)
            if (left_i <= right_prev) {
                // Jo hissa common hai, wo pichla wala gin chuka hai. Toh hum usko minus kar denge!
                added_left -= getW(left_i, right_prev, cnt, map); 
            }
        }
        int ans_left = added_left + rec(i + 1, 0, cnt, map);


        // ==========================================
        // OPTION 2: CURRENT ROBOT FIRES RIGHT
        // ==========================================
        int added_right = getW(pos_i, right_i, cnt, map);
        
        if (lastDir == 0) { // Pichle ne LEFT fire kiya tha
            // Koi overlap nahi, dono opposite bhaag rahe hain
        } else if (lastDir == 1) { // Pichle ne RIGHT fire kiya tha
            if (pos_i == right_prev) {
                added_right -= getW(pos_i, pos_i, cnt, map); // Single point overlap
            }
        }
        int ans_right = added_right + rec(i + 1, 1, cnt, map);

        // Dono choices mein se MAX return kar do
        return dp[i][lastDir] = Math.max(ans_left, ans_right);
    }

    public int maxWalls(int[] r, int[] d, int[] w) {
        n = r.length;
        int[][] robo = new int[n][2];
        for (int i = 0; i < n; i++) {
            robo[i][0] = r[i];
            robo[i][1] = d[i];
        }
        Arrays.sort(robo, (a, b) -> Integer.compare(a[0], b[0]));

        L = new int[n];
        R = new int[n];
        pos = new int[n];
        TreeSet<Integer> set = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            pos[i] = robo[i][0];
            L[i] = i > 0 ? Math.max(pos[i - 1], pos[i] - robo[i][1]) : Math.max(0, pos[i] - robo[i][1]);
            R[i] = i + 1 < n ? Math.min(robo[i + 1][0], pos[i] + robo[i][1]) : pos[i] + robo[i][1];

            set.add(pos[i]);
            set.add(L[i]);
            set.add(R[i]);
        }
        for (int x : w) set.add(x);

        Map<Integer, Integer> map = new HashMap<>();
        int idx = 0;
        for (int val : set) map.put(val, idx++);

        int[] cnt = new int[idx];
        for (int x : w) cnt[map.get(x)]++;
        for (int i = 1; i < idx; i++) cnt[i] += cnt[i - 1];

        // dpization Table [n robots][3 states] -> states: 0(left), 1(right), 2(start)
        dp = new Integer[n][3];

        return rec(0, 2, cnt, map);
    }
}