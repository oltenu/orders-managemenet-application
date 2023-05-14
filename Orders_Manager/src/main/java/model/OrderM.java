package model;

import lombok.Data;

@Data
public class OrderM {
    private long id;
    private long productId;
    private long clientId;
    private long billId;
    private int amount;

    public OrderM() {

    }

    public OrderM(long id, long productId, long clientId, long billId, int amount) {
        this.id = id;
        this.productId = productId;
        this.clientId = clientId;
        this.billId = billId;
        this.amount = amount;
    }
}
