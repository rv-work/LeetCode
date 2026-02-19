
class Solution {
    class Worker {
        int quality;
        int wage;
        double ratio;

        public Worker(int q, int w) {
            this.quality = q;
            this.wage = w;
            this.ratio = (double) w / q;
        }
    }

    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        Worker[] workers = new Worker[n];

        for (int i = 0; i < n; i++) {
            workers[i] = new Worker(quality[i], wage[i]);
        }

        //  Sort workers by Ratio (Ascending)
        // Reason: Jo current worker hoga loop me, wo highest ratio wala hoga (Captain)
        Arrays.sort(workers, (a, b) -> Double.compare(a.ratio, b.ratio));

        // Max Heap for Quality
        // Hame 'k' workers chahiye jinki Quality ka Sum MINIMUM ho.
        // Agar k se jyada log ho gaye, to jiski quality sabse jyada hai use nikal do.
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a); // Max Heap

        double sumQuality = 0;
        double minCost = Double.MAX_VALUE;

        for (Worker worker : workers) {
            // Step A: Current worker ko pool me add karo
            sumQuality += worker.quality;
            pq.add(worker.quality);

            // Step B: Agar k se jyada log hain, to sabse mehenge (high quality) wale ko hatao
            if (pq.size() > k) {
                sumQuality -= pq.poll();
            }

            // Step C: Agar exact k log hain, to cost calculate karo
            if (pq.size() == k) {
                // Cost = Total Quality * Current Worker's Ratio
                // Current worker ka ratio hi kyu? Kyunki array sorted hai, 
                // to is group me yahi 'sabse bada' ratio hai jo sabki demand puri karega.
                double currentCost = sumQuality * worker.ratio;
                minCost = Math.min(minCost, currentCost);
            }
        }

        return minCost;
    }
}



// mujhe chahiye ki obvious sabse chhota ration ... taki sabse chhota amount mai pay kru to ... agar mai group ka ratio km rkhunga to like phle vale ka ratio jitna km hoga utna hi dena pdega mujhe bakiyo .. ko but isme ye problem hai ki ... agar ratio jyada chhota ho to ho skta hai vo kisi worker ke liye uski wage ko fullfilll na kr paye to vo possible nhi hoga ..... to minimum to lena hai but .... in short vo chhota se chhota ratio jo at least k workers ke ratio se jyada ho.... taki unki wage fullfill ho jaye ..kyunki kisi ka bhi khud ka ratio jo bhi hai agar usse km ratio se multiply kiya gya uski quality se to vo wage se km hi hoga invalid .... isiliye ....../
// so the first step is ki mai vo ratio find kru jo k workers ko satisfy kre..... uske baad aise ek se jyada ratio bhi ho skte to ab hme ye ensure krna hai ki hm minimum total vala pick krein aur minimum ke liye chhhote se chhota quality vala chahiye hoga mujhe ....to uske liye hme simulate hi krna pdega ki wk window type lekar ki kaha minimum mil rha hai  .... 


// 1st option apply BS on answers....
// for each check ki isse possible hai kya pick krna ya nhi .. if yes then go for smaller ones.. if no then go for bigger one...

// 2nd option we can also go with max heap...
