package activity_6;

/*
 * Lucy Zhang
 * Activity 6
 * Bankers Algorithm - Driver Class
 */
public class Driver {
    private static final int RESOURCES = 10;
    private static final int CLIENTS = 3;
    private static final int[] CLIENT_CLAIMS = {6, 5, 7};   // nUnits
    private static final int[] CLIENT_REQUESTS = {5, 5, 5}; // nRequests
    private static final long MIN_SLEEP = 1000;
    private static final long MAX_SLEEP = 3000;
    public static void main(String[] args) {
        Banker banker = new Banker(RESOURCES);

        Client[] clients = new Client[CLIENTS];
        for (int i = 0; i < CLIENTS; i++) {
            String name = "Client " + (i+1);
            clients[i] = new Client(name, banker, CLIENT_CLAIMS[i], CLIENT_REQUESTS[i], MIN_SLEEP, MAX_SLEEP);
        }

        // start all client threads
        for (Client client : clients) {
            client.start();
        }

        // wait for all clients to complete
        for (Client client : clients) {
            try {
                client.join();
            } catch (InterruptedException e) {
                System.out.println(client.getName() + " was interrupted.");
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("All clients have finished!");
    }
}