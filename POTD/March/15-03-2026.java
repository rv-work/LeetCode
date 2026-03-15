import java.util.Arrays;

class Fancy {
    int MAX_N = 100005;
    long[] tree;
    long[] lazyAdd;
    long[] lazyMul;
    int length;
    final long MOD = 1000000007;

    public Fancy() {
        tree = new long[4 * MAX_N];
        lazyAdd = new long[4 * MAX_N];
        lazyMul = new long[4 * MAX_N];
        length = 0;
        Arrays.fill(lazyMul, 1);
    }
    
    private void pushDown(int node) {
        if (lazyMul[node] != 1 || lazyAdd[node] != 0) {
            int left = 2 * node;
            int right = 2 * node + 1;

            lazyMul[left] = (lazyMul[left] * lazyMul[node]) % MOD;
            lazyAdd[left] = (lazyAdd[left] * lazyMul[node] + lazyAdd[node]) % MOD;

            lazyMul[right] = (lazyMul[right] * lazyMul[node]) % MOD;
            lazyAdd[right] = (lazyAdd[right] * lazyMul[node] + lazyAdd[node]) % MOD;

            lazyMul[node] = 1;
            lazyAdd[node] = 0;
        }
    }

    private void updatePoint(int node, int start, int end, int idx, long val) {
        if (start == end) {
            tree[node] = val;
            lazyMul[node] = 1;
            lazyAdd[node] = 0;
            return;
        }
        pushDown(node);
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            updatePoint(2 * node, start, mid, idx, val);
        } else {
            updatePoint(2 * node + 1, mid + 1, end, idx, val);
        }
    }

    private void updateRange(int node, int start, int end, int l, int r, long addVal, long mulVal) {
        if (l > end || r < start) return;
        
        if (l <= start && end <= r) {
            lazyMul[node] = (lazyMul[node] * mulVal) % MOD;
            lazyAdd[node] = (lazyAdd[node] * mulVal + addVal) % MOD;
            return;
        }
        
        pushDown(node);
        int mid = start + (end - start) / 2;
        updateRange(2 * node, start, mid, l, r, addVal, mulVal);
        updateRange(2 * node + 1, mid + 1, end, l, r, addVal, mulVal);
    }

    private long queryPoint(int node, int start, int end, int idx) {
        if (start == end) {
            return (tree[node] * lazyMul[node] + lazyAdd[node]) % MOD;
        }
        pushDown(node);
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            return queryPoint(2 * node, start, mid, idx);
        } else {
            return queryPoint(2 * node + 1, mid + 1, end, idx);
        }
    }

    public void append(int val) {
        updatePoint(1, 0, MAX_N, length, val);
        length++;
    }
    
    public void addAll(int inc) {
        if (length > 0) {
            updateRange(1, 0, MAX_N, 0, length - 1, inc, 1);
        }
    }
    
    public void multAll(int m) {
        if (length > 0) {
            updateRange(1, 0, MAX_N, 0, length - 1, 0, m);
        }
    }
    
    public int getIndex(int idx) {
        if (idx >= length) return -1;
        return (int) queryPoint(1, 0, MAX_N, idx);
    }
}

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy obj = new Fancy();
 * obj.append(val);
 * obj.addAll(inc);
 * obj.multAll(m);
 * int param_4 = obj.getIndex(idx);
 */