package model;

import lombok.Data;

@Data
public class Product {
    private long id;
    private String name;
    private int amount;
    private double price;
    private String manufacturer;

    public Product() {
    }

    public Product(long id, String name, int amount, double price, String manufacturer) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.manufacturer = manufacturer;
    }
}
