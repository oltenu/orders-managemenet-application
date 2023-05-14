package logic;

import data.access.OrderDAO;
import model.*;
import validator.OrderValidator;

import java.util.List;

/**
 * Business logic class of OrderM class.
 * Contains a validator and a data access object.
 */
public class OrderBLL {
    private final OrderValidator orderValidator;
    private final OrderDAO orderDAO;

    public OrderBLL() {
        orderValidator = new OrderValidator();
        orderDAO = new OrderDAO();
    }

    /**
     * Creates and validates an order which in the end is inserted into database.
     *
     * @param product    Product of the order.
     * @param clientM    Client receiving the product.
     * @param billBLL    Data access object of the Bill class.
     * @param productBLL Data access object of the product class.
     * @param amount     Amount contained in the order.
     * @return True if the insert was successful, False otherwise.
     */
    public boolean insertOrder(Product product, ClientM clientM, BillBLL billBLL, ProductBLL productBLL, int amount) {
        Bill bill = billBLL.createBill(product, amount);
        OrderM orderM = new OrderM(orderValidator.getCurrentId(), product.getId(), clientM.getId(), bill.id(), amount);
        if (!orderValidator.validateOrder(orderM, product)) {
            return false;
        }
        orderDAO.insert(orderM);
        int newAmount = product.getAmount() - amount;
        product.setAmount(newAmount);
        productBLL.updateProduct(product.getId(), product.getProductName(), product.getAmount(), product.getPrice(), product.getManufacturer());
        billBLL.saveBill();

        return true;
    }

    public List<OrderM> findAllOrders() {
        return orderDAO.findAll();
    }
}
