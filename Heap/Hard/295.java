
class MedianFinder {
    
    // Left side (Smaller numbers) -> Max Heap chahiye taaki sabse bada top pe rahe
    PriorityQueue<Integer> leftMaxHeap;
    
    // Right side (Larger numbers) -> Min Heap chahiye taaki sabse chhota top pe rahe
    PriorityQueue<Integer> rightMinHeap;

    public MedianFinder() {
        leftMaxHeap = new PriorityQueue<>(Collections.reverseOrder());
        rightMinHeap = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        // Step 1: Naya number hamesha pehle Left (Max-Heap) mein dalo
        leftMaxHeap.add(num);
        
        // Step 2: Filter karo
        // Aisa ho sakta hai ki jo number abhi Left me dala hai, wo Right ke numbers se bada ho.
        // Isliye Left ka sabse bada number nikal kar Right me daal do.
        rightMinHeap.add(leftMaxHeap.poll());
        
        // Step 3: Balance karo
        // Hum chahte hain ki Left Heap ya to Right ke barabar ho, ya usse 1 bada ho.
        // Agar Right bada ho gaya, to uska sabse chhota element wapas Left ko de do.
        if (rightMinHeap.size() > leftMaxHeap.size()) {
            leftMaxHeap.add(rightMinHeap.poll());
        }
    }
    
    public double findMedian() {
        // Agar total numbers Odd hain, to Left ke paas 1 extra element hoga
        if (leftMaxHeap.size() > rightMinHeap.size()) {
            return leftMaxHeap.peek();
        } 
        // Agar Even hain, to dono ke top elements ka average nikal lo
        else {
            return (leftMaxHeap.peek() + rightMinHeap.peek()) / 2.0;
        }
    }
}







// Agar main har baar naya number aane par Array ko sort karunga, toh Time Limit Exceeded (TLE) aa jayegi. Mujhe kuch aisa chahiye jo naya number tezi se store kar le aur beech ka number (Median) turant bata de.

// The Intuition (Meri soch kya honi chahiye?) 

// Median ka matlab kya hota hai? Ek sorted line ke bilkul beech wala aadmi (element).

// Mujhe poori line se matlab nahi hai! Mujhe sirf beech ke 2 logo se matlab hai (ek Left side ka sabse bada, aur ek Right side ka sabse chhota).

// Is kaam ke liye 2 Priority Queues (Heaps) sabse best rahenge:

// Left Half (Max-Heap): Ye array ke saare chhote numbers ko rakhega. Iska peek() mujhe chhote hisse ka sabse bada number dega.

// Right Half (Min-Heap): Ye array ke saare bade numbers ko rakhega. Iska peek() mujhe bade hisse ka sabse chhota number dega.

// Balance ka Niyam (The Golden Rule):

// Main hamesha koshish karunga ki dono Heaps ka size barabar rahe. Agar total numbers Odd hain, toh main Left Half mein 1 number zyada rakhunga (taaki median nikalte time seedha Left ka peek le saku).