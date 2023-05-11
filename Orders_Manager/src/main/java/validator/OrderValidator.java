package validator;

import data.access.AbstractDAO;
import model.OrderM;
import model.Product;

public class OrderValidator {
    public boolean validateOrder(OrderM orderM, Product product) {
        if (orderM.getAmount() > 0 && orderM.getAmount() <= product.getAmount()) {
            product.setAmount(product.getAmount() - orderM.getAmount());
            return true;
        }
        return false;
    }

    public long getCurrentId() {
        long currentId = AbstractDAO.selectMaxId(2);
        return ++currentId;
    }
}
