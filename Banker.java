package activity_6;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Lucy Zhang
 * Activity 6
 * Bankers Algorithm - Banker Class
 */
public class Banker {
    private int totalUnits;
    private int availableUnits;
    private Map<Thread, Integer> claims = new HashMap<>();
    private Map<Thread, Integer> allocated = new HashMap<>();

    public Banker(int nUnits) {
        totalUnits = nUnits;
        availableUnits = nUnits;
    }

    /*
     * Attempts to register a claim for up to nUnits of resource.
     */
    public synchronized void setClaim(int nUnits) {
        Thread thread = Thread.currentThread();
        if(claims.containsKey(thread) || nUnits < 0 || nUnits > totalUnits) {
            System.exit(1);
        }

        // register the claim
        claims.put(thread, nUnits);
        allocated.put(thread, 0);

        System.out.println("Thread " + thread.getName() + " sets a claim for " + nUnits + " units.");
    }

    /*
     * Determines if allocating the resources results in a safe state.
     * Helper function for request.
     */
    public boolean safeToAllocate(Thread thread, int nUnits) {
        // not safe
        if (nUnits > availableUnits) {
            return false;
        }

        // available resources
        int available = availableUnits - nUnits;

        // Stimulates trial allocations to make sure it's safe
        Map<Thread, Integer> trial = new HashMap<>(allocated);
        trial.put(thread, trial.get(thread) + nUnits);

        // tracks the threads that still need resources
        Set<Thread> unfinished = new HashSet<>(claims.keySet());

        while (!unfinished.isEmpty()) {
            boolean finish = false;
            
            for (Thread t : new HashSet<>(unfinished)) {
                int need = claims.get(t) - trial.get(t);
                // stimulates that a thread can finish and release all its resources
                if (need <= available) {
                    available += trial.get(t);
                    unfinished.remove(t);
                    finish = true;
                }
            }

            // no thread can finish - unsafe
            if (!finish) {
                return false;
            }
        }
        // everyone can finish
        return true;
    }

    /*
     * The current thread requests nUnits more resources.
     */
    public synchronized boolean request(int nUnits) {
        Thread thread = Thread.currentThread();
        if(!claims.containsKey(thread) || nUnits <= 0 || nUnits > remaining()) {
            System.exit(1);
        }

        System.out.println("Thread " + thread.getName() + " requests " + nUnits + " units.");

        while(!safeToAllocate(thread, nUnits)) {
            try {
                System.out.println("Thread " + thread.getName() + " waits.");
                wait();
                System.out.println("Thread " + thread.getName() + " awakened.");

            } catch(InterruptedException e){}
        } 
        // safe to allocate
        System.out.println("Thread " + thread.getName() + " has " + nUnits + " units allocated.");
        allocated.put(thread, allocated.get(thread) + nUnits);
        availableUnits -= nUnits;
        return true;
    }

    /*
     * The current thread releases nUnits resources.
     */
    public synchronized void release(int nUnits) {
        Thread thread = Thread.currentThread();
        if(!claims.containsKey(thread) || nUnits <= 0 || nUnits > allocated.get(thread)) {
            System.exit(1);
        }

        System.out.println("Thread " + thread.getName() + " releases " + nUnits + " units.");

        // release nUnits
        allocated.put(thread, allocated.get(thread) - nUnits);
        availableUnits += nUnits;
        notifyAll();
    }

    /*
     * Returns the number of units allocated to the current thread.
     */
    public synchronized int allocated() {
        Thread thread = Thread.currentThread();
        return allocated.get(thread);
    }

    /*
     * Returns the number of units remaining in the current thread's claim.
     */
    public synchronized int remaining() {
        Thread thread = Thread.currentThread();
        return claims.get(thread) - allocated.get(thread);
    }
}