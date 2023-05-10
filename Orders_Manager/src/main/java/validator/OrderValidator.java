package validator;

import model.Order;
import model.Product;

public class OrderValidator {
    private long currentId = 0;

    public boolean validateOrder(Order order, Product product){
        if (order.getAmount() > 0 && order.getAmount() <= product.getAmount()){
            product.setAmount(product.getAmount() - order.getAmount());
            return true;
        }
        return false;
    }

    public long getCurrentId(){
        return currentId++;
    }
}
