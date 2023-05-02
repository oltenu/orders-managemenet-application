package model;

import lombok.Data;

@Data
public class Product {
    private long productId;
    private String name;
    private int amount;
    private double price;
    private String manufacturer;

    public Product(long productId, String name, int amount, double price, String manufacturer) {
        this.productId = productId;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.manufacturer = manufacturer;
    }
}
