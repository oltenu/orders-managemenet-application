package validator;

import data.access.AbstractDAO;
import model.Product;

/**
 * It is a validator class for a product object, it also generates an ID for a new object.
 */
public class ProductValidator {
    public boolean validateProduct(Product product) {
        return product.getAmount() >= 0 && product.getPrice() > 0;
    }

    public long getCurrentId() {
        long currentId = AbstractDAO.selectMaxId(3);
        return ++currentId;
    }
}
