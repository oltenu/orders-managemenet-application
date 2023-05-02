import model.*;

public class Main {
    public static void main(String[] args) {
        Client client = new Client(2, "Darius", "Oltean", 20, "Cluj", "5151515");
        System.out.println(client.getAddress());
    }
}
