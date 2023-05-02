package model;

import lombok.Data;

@Data
public class Order {
    private long orderId;
    private long productId;
    private long clientId;
    private long billId;
    private int amount;

    public Order(long orderId, long productId, long clientId, long billId, int amount) {
        this.orderId = orderId;
        this.productId = productId;
        this.clientId = clientId;
        this.billId = billId;
        this.amount = amount;
    }
}
