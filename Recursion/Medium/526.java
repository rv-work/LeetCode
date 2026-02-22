class Solution {

    int res(int idx, int n, Set<Integer> set) {

        if (idx > n) return 1;

        int cnt = 0;

        for (int i = 1; i <= n; i++) {

            if (set.contains(i)) continue;

            if (i % idx != 0 && idx % i != 0) continue;

            set.add(i);
            cnt += res(idx + 1, n, set);
            set.remove(i);
        }

        return cnt;
    }

    public int countArrangement(int n) {
        int cnt = 0;
        Set<Integer> set = new HashSet<>();

        for (int i = 1; i <= n; i++) {
            set.add(i);
            cnt += res(2, n, set);
            set.remove(i);
        }

        return cnt;
    }
}