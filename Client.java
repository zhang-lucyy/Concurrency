package activity_6;

import java.util.Random;

/*
 * Lucy Zhang
 * Activity 6
 * Bankers Algorithm - Client Class
 */
public class Client extends Thread {
    private String name;
    private Banker banker;
    private int nUnits;
    private int nRequests;
    private long minSleepMillis;
    private long maxSleepMillis;

    private Random random = new Random();

    public Client(String name, Banker banker, int nUnits, int nRequests, long minSleepMillis, long maxSleepMillis) {
        super(name);
        this.banker = banker;
        this.nUnits = nUnits;
        this.nRequests = nRequests;
        this.minSleepMillis = minSleepMillis;
        this.maxSleepMillis = maxSleepMillis;
    }

    @Override
    public void run() {
        // register a claim for up to nUnits
        banker.setClaim(nUnits);

        for(int i = 1; i <= nRequests; i++) {
            if (banker.remaining() == 0) {
                // release all units
                int units = banker.allocated();
                banker.release(units);
            } else {
                // request some units
                int request = random.nextInt(banker.remaining()) + 1;
                banker.request(request);
            }
            try {
                Thread.sleep(minSleepMillis + random.nextInt((int)(maxSleepMillis-minSleepMillis)+1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // release any units still allocated
        int units = banker.allocated();
        if (units > 0) {
            banker.release(units);
        }
    }
}