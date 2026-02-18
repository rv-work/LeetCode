package Heap.Easy;

public class 703 {
  
}
class KthLargest {
    PriorityQueue<Integer> pq;
    int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<>(); // min heap

        for (int val : nums) {
            pq.add(val);
            if (pq.size() > k) {
                pq.poll(); // remove smallest
            }
        }
    }
    
    public int add(int val) {
        pq.add(val);
        if (pq.size() > k) {
            pq.poll();
        }
        return pq.peek(); // kth largest
    }
}
