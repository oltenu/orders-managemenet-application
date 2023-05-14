import logic.*;
import view.Controller;
import view.View;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        BillBLL billBLL = new BillBLL();
        ClientBLL clientBLL = new ClientBLL();
        OrderBLL orderBLL = new OrderBLL();
        ProductBLL productBLL = new ProductBLL();

        new Controller(billBLL, clientBLL, orderBLL, productBLL, view);

        view.setVisible(true);
    }
}
