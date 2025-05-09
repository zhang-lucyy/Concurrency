package activity_5;

/*
 * Lucy Zhang
 * SWEN342 - Activity 5
 */
public interface IFork {
    /*
     * A philosopher (attempts to) acquire the fork.
     */
    public void acquire();

    /*
     * A philosopher releases the fork.
     */
    public void release();
}
