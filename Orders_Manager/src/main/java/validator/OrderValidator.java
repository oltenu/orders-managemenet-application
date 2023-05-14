package validator;

import data.access.AbstractDAO;
import model.OrderM;
import model.Product;

public class OrderValidator {
    public boolean validateOrder(OrderM orderM, Product product) {
        return orderM.getAmount() > 0 && orderM.getAmount() <= product.getAmount();
    }

    public long getCurrentId() {
        long currentId = AbstractDAO.selectMaxId(2);
        return ++currentId;
    }
}
