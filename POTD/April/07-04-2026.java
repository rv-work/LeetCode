class Robot {
    int n, m;
    int d;
    int x, y;
    String arr[];
    int peri;

    public Robot(int w, int h) {
        arr = new String[] { "North", "East", "South", "West" };
        n = h;
        m = w;
        d = 1;
        x = 0;
        y = 0;
        peri = 2 * (w - 1) + 2 * (h - 1);
    }

    public void step(int num) {
        
        num =  num % peri;

        if (num == 0 && x == 0 && y == 0) {
            d = 2; 
            return; 
        }

        while (num > 0) {
            if (d == 0) {
                int corner = n - 1 - y;

                if (num > corner) {
                    d = 3;
                    num -= corner;
                    y = n-1;;

                } else {
                    y += num;
                    num = 0;

                }

            } else if (d == 1) {
                int corner = m - 1 - x;

                if (num > corner) {
                    d = 0;
                    num -= corner;
                    x = m-1;

                } else {
                    x += num;
                    num = 0;

                }

            } else if (d == 2) {
                int corner = y;

                if (num > corner) {
                    d = 1;
                    num -= corner;
                    y = 0;

                } else {
                    y -= num;
                    num = 0;

                }

            } else if (d == 3) {
                int corner = x;

                if (num > corner) {
                    d = 2;
                    num -= corner;
                    x = 0;

                } else {
                    x -= num;
                    num = 0;

                }

            }
        }
    }

    public int[] getPos() {
        return new int[] { x, y };
    }

    public String getDir() {
        return arr[d];
    }
}

/**
 * Your Robot object will be instantiated and called as such:
 * Robot obj = new Robot(width, height);
 * obj.step(num);
 * int[] param_2 = obj.getPos();
 * String param_3 = obj.getDir();
 */