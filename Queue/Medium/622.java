class MyCircularQueue {
    
    int [] arr;
    int r;

    public MyCircularQueue(int k) {
        arr = new int[k];
        r = -1;
    }
    
    public boolean enQueue(int value) {
        if(r==arr.length-1) return false;
        arr[++r] = value;

        return true;
    }
    
    public boolean deQueue() {
        if(r == -1) return false;
        for(int i = 0; i<arr.length - 1; i++){
            arr[i] = arr[i+1];
        }
        r--;
        return true;
    }
    
    public int Front() {
        if(r==-1) return -1;
        return arr[0];
    }
    
    public int Rear() {
        if(r==-1) return -1;
        return arr[r];
    }
    
    public boolean isEmpty() {
        return r == -1;
    }
    
    public boolean isFull() {
        return r == arr.length-1;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */