package logic;

import data.access.OrderDAO;
import model.Bill;
import model.Client;
import model.Order;
import model.Product;
import validator.OrderValidator;

import java.util.List;

public class OrderBLL {
    private final OrderValidator orderValidator;
    private final OrderDAO orderDAO;

    public OrderBLL(){
        orderValidator = new OrderValidator();
        orderDAO = new OrderDAO();
    }

    public boolean insertOrder(Product product, Client client, BillBLL billBLL, int amount){
        Bill bill = billBLL.createBill(product, amount);
        Order order = new Order(orderValidator.getCurrentId(), product.getId(), client.getId(), bill.id(), amount);
        if(!orderValidator.validateOrder(order, product))
            return false;
        orderDAO.insert(order);
        billBLL.saveBill();

        return true;
    }

    public Order findOrder(long id){
        return orderDAO.findById(id);
    }

    public List<Order> findAllOrders(){
        return orderDAO.findAll();
    }

    public void deleteOrder(long id){
        orderDAO.delete(id);
    }
}
