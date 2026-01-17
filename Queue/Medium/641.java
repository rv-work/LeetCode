class MyCircularDeque {

    int arr[];
    int size;
    int rear;
    int front;

    public MyCircularDeque(int k) {
        
        this.size = k;
        arr = new int[size];
        rear = - 1;
        front = - 1;

    }
    
    public boolean insertFront(int value) {
        
        if(isFull()) {
            return false;
        }

        if(isEmpty()) {
            front = rear = 0;
            arr[front] = value;
            return true;
        }

        front = (front - 1 + size) % size;
        arr[front] = value;
        return true;

    }
    
    public boolean insertLast(int value) {
        
        if(isFull()) {
            return false;
        }

        if(isEmpty()) {
            front = rear = 0;
            arr[rear] = value;
            return true;
        }

        rear = (rear + 1) % size;
        arr[rear] = value;
        return true;

    }
    
    public boolean deleteFront() {
        
        if(isEmpty()) {
            return false;
        }

        if(front == rear) {
            front = rear = - 1;
            return true;
        }

        front = (front + 1) % size;
        return true;

    }
    
    public boolean deleteLast() {
        
        if(isEmpty()) {
            return false;
        }

        if(front == rear) {
            front = rear = - 1;
            return true;
        }

        rear = (rear - 1 + size) % size;
        return true;

    }
    
    public int getFront() {
        if(isEmpty()) {
            return - 1;
        }

        return arr[front];
    }
    
    public int getRear() {
        if(isEmpty()) {
            return - 1;
        }

        return arr[rear];
    }
    
    public boolean isEmpty() {

        return front == - 1 && rear == - 1;

    }
    
    public boolean isFull() {

        return front == (rear + 1) % size;

    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */