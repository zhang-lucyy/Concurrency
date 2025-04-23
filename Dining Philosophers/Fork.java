package activity_5;

import java.util.concurrent.Semaphore;

/*
 * Lucy Zhang
 * SWEN342 - Activity 5
 */
public class Fork implements IFork{
    private Semaphore mutex;

    public Fork() {
        // represents 1 fork
        this.mutex = new Semaphore(1);
    }

    /*
     * A philosopher (attempts to) acquire the fork.
     */
    @Override
    public void acquire() {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    /*
     * A philosopher releases the fork.
     */
    @Override
    public void release() {
        mutex.release();
    }
}
