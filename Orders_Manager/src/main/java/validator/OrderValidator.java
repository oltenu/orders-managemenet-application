package validator;

import data.access.AbstractDAO;
import model.*;

/**
 * It is a validator class for a order object, it also generates an ID for a new object.
 */
public class OrderValidator {
    public boolean validateOrder(OrderM orderM, Product product) {
        return orderM.getAmount() > 0 && orderM.getAmount() <= product.getAmount();
    }

    public long getCurrentId() {
        long currentId = AbstractDAO.selectMaxId(2);
        return ++currentId;
    }
}
