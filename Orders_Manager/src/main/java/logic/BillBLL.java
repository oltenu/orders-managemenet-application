package logic;

import data.access.BillDAO;
import model.*;
import validator.BillValidator;

import java.util.List;

public class BillBLL {
    private final BillValidator billValidator;
    private final BillDAO billDAO;
    private Bill bill;

    public BillBLL() {
        billValidator = new BillValidator();
        billDAO = new BillDAO();
    }

    public Bill createBill(Product product, int amount) {
        double price = amount * product.getPrice();
        bill = new Bill(billValidator.getCurrentId(), price);
        return bill;
    }

    public void saveBill() {
        billDAO.insert(bill);
    }

    public Bill findBill(long id) {
        return billDAO.findById(id);
    }

    public List<Bill> findAllBills() {
        return billDAO.findAll();
    }
}
