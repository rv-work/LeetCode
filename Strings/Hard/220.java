
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {

        TreeSet<Long> window = new TreeSet<>();  

        for (int i = 0; i < nums.length; i++) {

            long x = nums[i];

            // see hme chahiye ki jo ye x hai ... to ek aisa number jisse x ka abs diff valueDiff tak ho .... to agar mai aise sochu ko x ke pichhe jao valueDiff and x ke aage jao valueDiff => x-valueDiff to x+valueDiff... and try to find whether any number exist in the window that lies between this range thats it
            
            // first step ki left vali limit se bda ho sabse chhota ( chhota taki bde right vale se chhote hone chance bdh jayein...)
            Long candidate = window.ceiling(x - valueDiff);

            // fir agar left se mil gya to right se chhota hai vo check kr rhe hain ...
            if (candidate != null && candidate <= x + valueDiff) {
                return true;
            }

            window.add(x);


            // ye vhi chij hai ki ek jab size indexDiff ke brabar ho gya to aage bdhte huye ek addd krenge aur bda hone pr remove to kyunki hme fix size me hain to jab ek bar yha reach kr jayenge to hr baar hi nikalna pdega isiliye jab indexDiff tak phuch gye size em to ab hr baar add aur remove krenge 
            if (i >= indexDiff) {
                window.remove((long) nums[i - indexDiff]);
            }
        }
        return false;
    }
}

