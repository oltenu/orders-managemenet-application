package validator;

import data.access.AbstractDAO;
import model.Product;

public class ProductValidator {
    public boolean validateProduct(Product product) {
        return product.getAmount() >= 0 && product.getPrice() > 0;
    }

    public long getCurrentId() {
        long currentId = AbstractDAO.selectMaxId(3);
        return ++currentId;
    }
}
