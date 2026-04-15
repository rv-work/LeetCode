class Solution {
    public int closestTarget(String[] w, String t, int s) {
        int n = w.length;
        int i = 0;
        while(i < n){
            if(w[(s+i)%n].equals(t) || w[(s-i+n)%n].equals(t)) return i;
            i++;
        }

        return -1;
    }
}