class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int n = moves.length();

        int dist1 = 0; // taking _ as R
        for(int i = 0; i<n; i++){
            if(moves.charAt(i) == 'L') dist1 -= 1;
            else dist1 += 1;
        }

        int dist2 = 0; // taking _ as L
        for(int i = 0; i<n; i++){
            if(moves.charAt(i) == 'R') dist2 += 1;
            else dist2 -= 1;
        }

        return Math.max(Math.abs(dist1) , Math.abs(dist2) );    
    }
}