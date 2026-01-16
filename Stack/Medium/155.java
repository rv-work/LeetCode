class MinStack {
    Stack<Integer> st;
    int minIndex = -1;
    int idx = -1;
    int minValue = Integer.MAX_VALUE;
    Map<Integer , Integer> map ;

    public MinStack() {
        st = new Stack<>();
        map = new HashMap<>();
    }
    
    public void push(int val) {
        idx++;
        map.put(idx , val);

        if (val <= minValue) {
            minValue = val;
            minIndex = idx;
        }

        st.push(minIndex);
    }
    
    public void pop() {
        int removedIndex = idx;
        map.remove(idx);
        st.pop();
        idx--;

        // 🔥 FIX: if minimum element was popped
        if (removedIndex == minIndex) {
            minValue = Integer.MAX_VALUE;
            minIndex = -1;

            for (int key : map.keySet()) {
                int v = map.get(key);
                if (v <= minValue) {
                    minValue = v;
                    minIndex = key;
                }
            }
        }
    }

    
    public int top() {
        return map.get(idx);
    }
    
    public int getMin() {
        return map.get(st.peek());
    }
}









class MinStack {
    Stack<int[]> st;

    public MinStack() {
        st = new Stack<>();
    }

    public void push(int val) {
        int min = st.isEmpty() ? val : Math.min(val, st.peek()[1]);
        st.push(new int[]{val, min});
    }

    public void pop() {
        st.pop();
    }

    public int top() {
        return st.peek()[0];
    }

    public int getMin() {
        return st.peek()[1];
    }
}


