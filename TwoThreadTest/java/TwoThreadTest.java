package java;
/**
 * Runs two instances of SimpleThread.
 */
public class TwoThreadTest {
    public static void main(String[] args) {
        SimpleThread thread1 = new SimpleThread("Hi");
        SimpleThread thread2 = new SimpleThread("Ho");

        thread1.start();
        thread2.start();
    }
}
