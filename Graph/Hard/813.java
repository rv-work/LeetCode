class Solution {

    class Pair {
        int bus;
        int dist;
        Pair(int bus, int dist){
            this.bus = bus;
            this.dist = dist;
        }
    }

    public int numBusesToDestination(int[][] routes, int source, int target) {

        if (source == target) return 0;

        int n = routes.length;
        int mat[][] = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(mat[i], (int)1e9);
            mat[i][i] = 0;
        }

        List<List<Integer>> routesSet = new ArrayList<>();
        Set<Integer> allStops = new HashSet<>();
        List<Set<Integer>> connectedBus = new ArrayList<>();

        // convert each route to List
        for (int[] route : routes) {
            List<Integer> temp = new ArrayList<>();
            for (int stop : route) temp.add(stop);
            routesSet.add(temp);
            for (int stop : route) allStops.add(stop);
        }

        // for each stop, find all buses containing that stop
        for (int stop : allStops) {
            Set<Integer> s = new HashSet<>();
            for (int i = 0; i < n; i++) {
                if (routesSet.get(i).contains(stop)) {
                    s.add(i);
                }
            }
            connectedBus.add(s);
        }

        Queue<Pair> q = new LinkedList<>();
        boolean vis[] = new boolean[n];

        // fill mat: all buses in a set have distance 1 with each other
        for (Set<Integer> set : connectedBus) {
            for (int bus1 : set) {

                // starting buses: contains source stop?
                if (routesSet.get(bus1).contains(source)) {
                    q.add(new Pair(bus1, 1));
                }

                for (int bus2 : set) {
                    if (bus1 != bus2) {
                        mat[bus1][bus2] = 1;
                        mat[bus2][bus1] = 1;
                    }
                }
            }
        }

      
        while (!q.isEmpty()) {
            Pair p = q.poll();
            int bus = p.bus;
            int dist = p.dist;
            vis[bus] = true;


            // check if this bus reaches target
            if (routesSet.get(bus).contains(target)) {
                return dist;
            }

            // visit neighbors
            for (int nextBus = 0; nextBus < n; nextBus++) {
                if (mat[bus][nextBus] == 1 && !vis[nextBus]) {
                    q.add(new Pair(nextBus, dist + 1));
                }
            }
        }

        return -1;
    }
}












class Solution {

    class Pair {
        int bus;
        int dist;
        Pair(int bus, int dist) {
            this.bus = bus;
            this.dist = dist;
        }
    }

    public int numBusesToDestination(int[][] routes, int source, int target) {

        if (source == target) return 0;

        int n = routes.length;

        // 1) bus → stops
        List<List<Integer>> busToStops = new ArrayList<>();
        for (int[] route : routes) {
            List<Integer> temp = new ArrayList<>();
            for (int stop : route) temp.add(stop);
            busToStops.add(temp);
        }

        // 2) stop → buses
        Map<Integer, List<Integer>> stopToBuses = new HashMap<>();
        for (int bus = 0; bus < n; bus++) {
            for (int stop : routes[bus]) {
                stopToBuses.putIfAbsent(stop, new ArrayList<>());
                stopToBuses.get(stop).add(bus);
            }
        }

        Queue<Pair> q = new LinkedList<>();
        boolean busVisited[] = new boolean[n];
        Set<Integer> stopVisited = new HashSet<>();

        // Add all buses that contain source
        if (stopToBuses.containsKey(source)) {
            for (int bus : stopToBuses.get(source)) {
                q.add(new Pair(bus, 1));
                busVisited[bus] = true;
            }
        }

        // BFS
        while (!q.isEmpty()) {
            Pair p = q.poll();
            int bus = p.bus;
            int dist = p.dist;

            // 1) Does this bus contain target?
            if (busToStops.get(bus).contains(target)) {
                return dist;
            }

            // 2) Explore all stops of this bus
            for (int stop : busToStops.get(bus)) {

                // avoid revisiting stops
                if (stopVisited.contains(stop)) continue;
                stopVisited.add(stop);

                // 3) For each stop, add every connected bus
                for (int nextBus : stopToBuses.get(stop)) {
                    if (!busVisited[nextBus]) {
                        busVisited[nextBus] = true;
                        q.add(new Pair(nextBus, dist + 1));
                    }
                }
            }
        }

        return -1;
    }
}













class Solution {

    public int numBusesToDestination(int[][] routes, int source, int target) {

        if (source == target) return 0;

        int n = routes.length;

        // stop → list of buses
        Map<Integer, List<Integer>> stopToBuses = new HashMap<>();

        for (int bus = 0; bus < n; bus++) {
            for (int stop : routes[bus]) {
                stopToBuses.putIfAbsent(stop, new ArrayList<>());
                stopToBuses.get(stop).add(bus);
            }
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] busVisited = new boolean[n];
        Set<Integer> stopVisited = new HashSet<>();

        // Start from all buses that contain the source
        if (stopToBuses.containsKey(source)) {
            for (int bus : stopToBuses.get(source)) {
                q.add(bus);
                busVisited[bus] = true;
            }
        }

        int busesTaken = 1;

        // BFS over buses
        while (!q.isEmpty()) {

            int size = q.size();

            while (size-- > 0) {

                int bus = q.poll();

                // check if this bus reaches target
                for (int stop : routes[bus]) {
                    if (stop == target) return busesTaken;
                }

                // explore all stops of this bus
                for (int stop : routes[bus]) {
                    if (stopVisited.contains(stop)) continue;
                    stopVisited.add(stop);

                    // take all buses connected to this stop
                    for (int nextBus : stopToBuses.get(stop)) {
                        if (!busVisited[nextBus]) {
                            busVisited[nextBus] = true;
                            q.add(nextBus);
                        }
                    }
                }
            }

            busesTaken++;
        }

        return -1;
    }
}
