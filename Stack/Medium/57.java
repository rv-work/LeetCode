class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;

        // special case
        if (n == 0) {
            return new int[][]{ newInterval };
        }

        int st = n;
        int end = n;

        // find st
        for (int i = 0; i < n; i++) {
            if (intervals[i][1] >= newInterval[0]) {
                st = i;
                break;
            }
        }

        // find end
        for (int i = st; i < n; i++) {
            if (intervals[i][0] > newInterval[1]) {
                end = i - 1;
                break;
            }
        }

        if (st == n) {
            // newInterval comes after all
            int[][] ans = new int[n + 1][2];
            for (int i = 0; i < n; i++) ans[i] = intervals[i];
            ans[n] = newInterval;
            return ans;
        }

        if (end == n) end = n - 1;

        // merge interval
        int[][] merged = new int[1][2];
        merged[0][0] = newInterval[0];
        merged[0][1] = newInterval[1];

        for (int i = st; i <= end; i++) {
            merged[0][0] = Math.min(merged[0][0], intervals[i][0]);
            merged[0][1] = Math.max(merged[0][1], intervals[i][1]);
        }

        // build answer
        int[][] ans = new int[n - (end - st)][2];
        int idx = 0;

        // before st
        for (int i = 0; i < st; i++) {
            ans[idx++] = intervals[i];
        }

        // merged interval
        ans[idx++] = merged[0];

        // after end
        for (int i = end + 1; i < n; i++) {
            ans[idx++] = intervals[i];
        }

        return ans;
    }
}







class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;

        // 1️⃣ create array of size n+1
        int[][] arr = new int[n + 1][2];

        for (int i = 0; i < n; i++) {
            arr[i] = intervals[i];
        }
        arr[n] = newInterval;

        // 2️⃣ sort by start time
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        // 3️⃣ merge intervals
        List<int[]> res = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (res.isEmpty() || res.get(res.size() - 1)[1] < arr[i][0]) {
                res.add(arr[i]);
            } else {
                res.get(res.size() - 1)[1] =
                    Math.max(res.get(res.size() - 1)[1], arr[i][1]);
            }
        }

        return res.toArray(new int[res.size()][]);
    }
}







class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // 1️⃣ Add all intervals before newInterval (no overlap)
        while (i < n && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i]);
            i++;
        }

        // 2️⃣ Merge in place (overlapping region)
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        // 3️⃣ Add merged interval
        res.add(newInterval);

        // 4️⃣ Add remaining intervals
        while (i < n) {
            res.add(intervals[i]);
            i++;
        }

        return res.toArray(new int[res.size()][]);
    }
}











class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {

        Stack<int[]> st = new Stack<>();

        for (int[] curr : intervals) {

            // 1️⃣ Before zone: no overlap & strictly left
            if (curr[1] < newInterval[0]) {
                st.push(curr);
            }

            // 2️⃣ After zone: current is strictly right (means new interval finished)
            else if (curr[0] > newInterval[1]) {
                st.push(newInterval);
                newInterval = curr;  // now treat curr as the new interval for rest
            }

            // 3️⃣ Overlap zone: merge with newInterval
            else {
                newInterval[0] = Math.min(newInterval[0], curr[0]);
                newInterval[1] = Math.max(newInterval[1], curr[1]);
            }
        }

        // push the last accumulated newInterval
        st.push(newInterval);

        // convert to output array
        int[][] ans = new int[st.size()][2];

        for (int i = st.size() - 1; i >= 0; i--) {
            ans[i] = st.pop();
        }

        return ans;
    }
}
