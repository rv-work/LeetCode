class MyStack {
    Queue<Integer> q;

    public MyStack() {
        q = new LinkedList<>();
    }
    
    public void push(int x) {
        q.add(x);
    }
    
    public int pop() {
        int size = q.size();
        for(int i = 1; i<= size-1; i++){
            q.add(q.poll());
        }
        return q.poll();
    }
    
    public int top() {
         int size = q.size();
        for(int i = 1; i<= size-1; i++){
            q.add(q.poll());
        }
        int top =  q.peek();
        q.offer(q.poll());
        return top;
    }
    
    public boolean empty() {
        return q.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */














class MyStack {

    private Queue<Integer> q;

    public MyStack() {
        q = new LinkedList<>();
    }

    public void push(int x) {
        q.add(x);
        for (int i = 0; i < q.size() - 1; i++) {
            q.add(q.poll());
        }
    }

    public int pop() {
        return q.poll();
    }

    public int top() {
        return q.peek();
    }

    public boolean empty() {
        return q.isEmpty();
    }
}













class MyStack {

    private Queue<Integer> q1;
    private Queue<Integer> q2;

    public MyStack() {
        q1 = new ArrayDeque<>();
        q2 = new ArrayDeque<>();
    }

    public void push(int x) {
        q1.add(x);
    }

    public int pop() {
        while (q1.size() > 1) {
            q2.add(q1.poll());
        }

        int poppedVal = q1.poll();
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;

        return poppedVal;
    }

    public int top() {
        while (q1.size() > 1) {
            q2.add(q1.poll());
        }

        int topVal = q1.peek();
        q2.add(q1.poll());
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;

        return topVal;
    }

    public boolean empty() {
        return q1.isEmpty();
    }
}