class MyQueue {
    Stack<Integer> prm ;
    Stack<Integer> sec ;
    public MyQueue() {
        prm = new Stack<>();
        sec = new Stack<>();
    }
    
    public void push(int x) {
        while(!prm.isEmpty()){
            sec.add(prm.pop());
        }
        prm.add(x);
        while(!sec.isEmpty()){
            prm.add(sec.pop());
        }

    }
    
    public int pop() {
        return prm.pop();
    }
    
    public int peek() {
        return prm.peek();
    }
    
    public boolean empty() {
        return prm.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */