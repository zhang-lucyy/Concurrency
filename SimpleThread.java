package java;
import java.util.Random;

/**
 * Lucy Zhang
 * SWEN342 - Activity 1
*/
public class SimpleThread extends Thread{
    SimpleThread (String name) {
        super(name);
    }

    @Override
    public void run() {
        for(int i = 1; i <= 10; i++) {
            try {
                Random random = new Random(System.currentTimeMillis()); // seed with current time
                int time = random.nextInt(1001); // random time btw 0-1 secs
                Thread.sleep(time);
            } catch (InterruptedException e) {
                System.out.println("Error, try again");
            }
            if(i < 10) {
                System.out.println(i + " " + getName());
            } else {
                System.out.println("DONE! " + getName());
            }
        }
    }
}