package activity_4;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Stimulates the bridge troll who controls passage across the bridge.
 */
public class Bridge {
    private int count;  // count of woolies on the bridge
    private Queue<Thread> queue;    // the order in which the woolies arrive

    public Bridge () {
        count = 0;
        queue = new LinkedList<>();
    }

    /**
     * When a woolie wants to cross the bridge.
     * Max 3 Woolies can be on the bridge at a time.
     */
    public synchronized void enterBridge(Woolie woolie) {
        queue.add(woolie);  // woolie lines up
        while (queue.peek() != woolie || count >= 3) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Try again.");
            }
        }
        queue.remove();
        count++;
    }

    /**
     * Notifies the troll that the Woolie is off the bridge.
     */
    public synchronized void leaveBridge() {
        count--;
        notifyAll();
    }   
}
