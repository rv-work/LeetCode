class Solution {

    double area(int[][] squares, double end) {
        double ans = 0;

        for (int i = 0; i < squares.length; i++) {
            double bottom = squares[i][1];
            double top = squares[i][1] + squares[i][2];

            if (end <= bottom) continue;

            double overlap = Math.min(end, top) - bottom;
            if (overlap > 0) {
                ans += overlap * squares[i][2];

            }
        }
        return ans;
    }

    double lowerBound(int[][] squares , double target , double min , double max){
        if (max - min < 1e-6) return min;

        double mid = min +( max-min )/2;
        double areaTillMid = area(squares, mid);

        if(areaTillMid >= target) return lowerBound(squares , target , min , mid);
        else return lowerBound(squares , target , mid , max);

    }


    public double separateSquares(int[][] squares) {
        int n = squares.length;
        double totalArea = 0;
        double yMin = Double.MAX_VALUE;
        double yMax = Double.MIN_VALUE;

        for(int i = 0; i<n; i++){
            int arr[] = squares[i];
            
            yMin = Math.min(yMin , arr[1] );
            yMax = Math.max(yMax , arr[1] + arr[2] );
            totalArea += (double)arr[2]*arr[2];
        } 

        double target = totalArea/2;
        
        return lowerBound(squares , target , yMin , yMax);

    }
}




















class Solution {

    double area(int[][] squares, double end) {
        double ans = 0;

        for (int i = 0; i < squares.length; i++) {
            int[] sq = squares[i];
            double bottom = sq[1];
            double side = sq[2];
            double top = bottom + side;

            if (end <= bottom)
                continue;

            double overlap = Math.min(end, top) - bottom;
            if (overlap > 0) {
                ans += overlap * side;
            }
        }
        return ans;
    }

    double lowerBound(int[][] squares, double target, double min, double max) {

        while (max - min > 1e-6) {
            double mid = (min + max) / 2;
            if (area(squares, mid) >= target)
                max = mid;
            else
                min = mid;
        }
        
        return min;

    }

    public double separateSquares(int[][] squares) {
        int n = squares.length;
        double totalArea = 0;
        double yMin = Double.MAX_VALUE;
        double yMax = Double.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int arr[] = squares[i];

            yMin = Math.min(yMin, arr[1]);
            yMax = Math.max(yMax, arr[1] + arr[2]);
            totalArea += (double) arr[2] * arr[2];
        }

        double target = totalArea / 2;

        return lowerBound(squares, target, yMin, yMax);

    }
}