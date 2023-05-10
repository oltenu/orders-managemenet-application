package validator;

import model.Product;

public class ProductValidator {
    private long currentId = -1;

    public boolean validateProduct(Product product){
        return product.getAmount() > 0;
    }

    public long getCurrentId(){
        return currentId++;
    }
}
