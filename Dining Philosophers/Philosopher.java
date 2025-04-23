package activity_5;

import java.util.Random;

/*
 * Lucy Zhang
 * SWEN342 - Activity 5
 */
public class Philosopher extends Thread {
    private int id;
    private Fork left;
    private Fork right;
    private boolean rHanded;    // true if right handed, false if left
    private int nTimes;         // num of think / eat cycles before philosopher exits
    private long thinkMillis;   // max think time for each cycle
    private long eatMillis;     // max eating time for each cycle

    public Philosopher(int id, Fork left, Fork right, boolean rHanded, int nTimes, long thinkMillis, long eatMillis) {
        this.id = id;
        this.left = left;
        this.right = right;
        this.rHanded = rHanded;
        this.nTimes = nTimes;
        this.thinkMillis = thinkMillis;
        this.eatMillis = eatMillis;
    }

    /*
     * Represents one cycle.
     */
    public void cycle() {
        Random random = new Random();
        int t = random.nextInt((int)thinkMillis + 1);    // 0-thinkMillis
        System.out.println("Philosopher " + id + " thinks for " + t + " time units");
        // sleep for the selected time
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            System.out.println("Error: " + e);
        }

        // right handed, attempts to go for fork
        if(rHanded) {
            System.out.println("Philosopher " + id + " goes for right fork");
            right.acquire();
            System.err.println("Philosopher " + id + " has right fork");
        }

        Thread.yield();
        System.out.println("Philosopher (both left handed and right handed) " + id + " goes for left fork");
        left.acquire();
        System.out.println("Philosopher " + id + " has left fork");
        Thread.yield();

        // left handed
        if(!rHanded) {
            System.out.println("Philosopher " + id + " goes for right fork");
            right.acquire();
            System.out.println("Philosopher " + id + " has right fork");
        }

        t = random.nextInt((int)eatMillis + 1); // 0-eatMillis
        System.out.println("Philosopher " + id + " eats for " + t + " time units");
        // sleep for the selected time
        try {
            Thread.sleep(t);
        } catch(InterruptedException e) {
            System.out.println("Error: " + e);
        }
        
        // release right and left fork
        right.release();
        System.out.println("Philosopher " + id + " releases right fork");
        left.release();
        System.out.println("Philosopher " + id + " releases left fork");
    }

    /*
     * Runs n cycles.
     */
    public void run() {
        // run infinitely
        while(nTimes == 0) {
            cycle();
        }

        // execute nTimes
        for(int i = 0; i < nTimes; i++) {
            cycle();
        }
    }
}
