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









class MyCircularQueue {

    int [] arr;
    int r;
    int f;
    int size ;

    public MyCircularQueue(int k) {
        arr = new int[k];
        r = -1;
        f = -1;
        size = k;
    }
    
    public boolean enQueue(int value) {
        if(isFull()) return false;
        if(isEmpty()) 
        {
            f = 0;
            r = 0;
        }
        else {
            r = (r+1)%size;
        }
        arr[r] = value;
        return true;
    }
    
    public boolean deQueue() {
        if(isEmpty()) return false;
        
        if(f == r ) {
            f =-1;
            r=-1;
        }
        else f = (f + 1)%size;
        
        return true;
    }
    
    public int Front() {
        if(isEmpty()) return -1;
        return arr[f];
    }
    
    public int Rear() {
        if(isEmpty()) return -1;
        return arr[r];
    }
    
    public boolean isEmpty() {
        return r == -1 && f == -1;
    }
    
    public boolean isFull() {
        return (r+1) % size == f;
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