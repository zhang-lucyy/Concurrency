package activity_4;

public class Woolie extends Thread {
    private int time;       // num of secs it takes to cross
    private String destination;     // merctan or sicstine
    private Bridge bridge;  // bridge troll

    public Woolie (String name, int time, String destination, Bridge bridge) {
        super(name);
        this.time = time;
        this.destination = destination;
        this.bridge = bridge;
    }

    /**
     * Stimulates a Woolie crossing the bridge.
     */
    @Override
    public void run() {
        System.out.println(getName() + " has arrived at the bridge.");
        bridge.enterBridge(this);
        System.out.println(getName() + " is starting to cross.");

        // executes once for every sec the woolie is on the bridge
        for(int i = 1; i <= this.time; i++) {
            try {
                Thread.sleep(1000); // stimulate 1 sec on the bridge
            } catch (InterruptedException e){
                System.out.println("Try again.");
            }
            System.out.println("\t" + getName() + " " + i + " seconds");
        }
        System.out.println(getName() + " leaves at " + this.destination + ".");
        bridge.leaveBridge();
    }
    public static void main(String[] args) {
        Bridge bridge = new Bridge();

        // experimenting with 10 woolies
        Woolie bob = new Woolie("Bob", 5, "Merctan", bridge);
        Woolie kevin = new Woolie("Kevin", 8, "Sicstine", bridge);
        Woolie stewart = new Woolie("Stewart", 3, "Merctan", bridge);
        Woolie gru = new Woolie("Gru", 10, "Sicstine", bridge);
        Woolie dave = new Woolie("Dave", 4, "Merctan", bridge);
        Woolie karl = new Woolie("Karl", 6, "Sicstine", bridge);
        Woolie jeff = new Woolie("Jeff", 9, "Merctan", bridge);
        Woolie steve = new Woolie("Steve", 7, "Sicstine", bridge);
        Woolie tom = new Woolie("Tom", 3, "Merctan", bridge);
        Woolie jerry = new Woolie("Jerry", 5,"Sicstine", bridge);

        bob.start();
        kevin.start();
        stewart.start();
        gru.start();
        dave.start();
        karl.start();
        jeff.start();
        steve.start();
        tom.start();
        jerry.start();
    }
}
