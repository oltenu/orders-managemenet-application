package logic;

import data.access.ProductDAO;
import model.Product;
import validator.ProductValidator;

import java.util.List;

public class ProductBLL {
    private final ProductValidator productValidator;
    private final ProductDAO productDAO;

    /**
     * Business logic class for Product class.
     */
    public ProductBLL() {
        productDAO = new ProductDAO();
        productValidator = new ProductValidator();
    }

    public boolean insertProduct(String name, int amount, double price, String manufacturer) {
        Product product = new Product(productValidator.getCurrentId(), name, amount, price, manufacturer);
        if (!productValidator.validateProduct(product))
            return false;

        productDAO.insert(product);

        return true;
    }

    public boolean updateProduct(long productId, String name, int amount, double price, String manufacturer) {
        Product product = new Product(productId, name, amount, price, manufacturer);
        if (!productValidator.validateProduct(product))
            return false;

        productDAO.update(product);

        return true;
    }

    public Product findProduct(long id) {
        return productDAO.findById(id);
    }

    public List<Product> findAllProducts() {
        return productDAO.findAll();
    }

    public void deleteProduct(long id) {
        productDAO.delete(id);
    }
}
