
class Solution {
    class SegmentTree {
        int[] minVal, maxVal, lazy;
        int n;

        public SegmentTree(int n) {
            this.n = n;
            minVal = new int[4 * n];
            maxVal = new int[4 * n];
            lazy = new int[4 * n];
        }

        void push(int node) {
            if (lazy[node] != 0) {
                
                lazy[2 * node] += lazy[node];
                minVal[2 * node] += lazy[node];
                maxVal[2 * node] += lazy[node];

                lazy[2 * node + 1] += lazy[node];
                minVal[2 * node + 1] += lazy[node];
                maxVal[2 * node + 1] += lazy[node];

                lazy[node] = 0;
            }
        }

        void update(int node, int start, int end, int l, int r, int val) {
            if (start > end || start > r || end < l) return;

            if (start >= l && end <= r) {
                lazy[node] += val;
                minVal[node] += val;
                maxVal[node] += val;
                return;
            }

            push(node);
            int mid = (start + end) / 2;
            update(2 * node, start, mid, l, r, val);
            update(2 * node + 1, mid + 1, end, l, r, val);

            minVal[node] = Math.min(minVal[2 * node], minVal[2 * node + 1]);
            maxVal[node] = Math.max(maxVal[2 * node], maxVal[2 * node + 1]);
        }

        int queryFirstZero(int node, int start, int end, int searchEnd) {
            if (start > searchEnd) return -1;

            if (minVal[node] > 0 || maxVal[node] < 0) return -1;

            if (start == end) return start;

            push(node);
            int mid = (start + end) / 2;

            int res = queryFirstZero(2 * node, start, mid, searchEnd);
            if (res != -1) return res;

            return queryFirstZero(2 * node + 1, mid + 1, end, searchEnd);
        }
    }

    public int longestBalanced(int[] nums) {
        int n = nums.length;
        SegmentTree st = new SegmentTree(n);
        Map<Integer, Integer> lastPos = new HashMap<>();
        int maxLen = 0;

        for (int right = 0; right < n; right++) {
            int num = nums[right];
            int prev = lastPos.getOrDefault(num, -1);
            

            int val = (num % 2 != 0) ? 1 : -1;

      
            st.update(1, 0, n - 1, prev + 1, right, val);
            
            lastPos.put(num, right);

            int left = st.queryFirstZero(1, 0, n - 1, right);
            
            if (left != -1) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }
        return maxLen;
    }
}