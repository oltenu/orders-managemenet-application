package model;

import lombok.Data;

/**
 * Models a product.
 */
@Data
public class Product {
    private long id;
    private String productName;
    private int amount;
    private double price;
    private String manufacturer;

    public Product() {

    }

    public Product(long id, String productName, int amount, double price, String manufacturer) {
        this.id = id;
        this.productName = productName;
        this.amount = amount;
        this.price = price;
        this.manufacturer = manufacturer;
    }
}
