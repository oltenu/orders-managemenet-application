package data.access;

import model.Bill;

import java.util.List;

public class BillDAO {
    private final AbstractDAO<Bill> abstractDAO;

    public BillDAO(){
        abstractDAO = new AbstractDAO<>();
    }

    public Bill findById(long id){
        return abstractDAO.findById(id);
    }

    public List<Bill> findAll(){
        return abstractDAO.findAll();
    }

    public void insert(Bill bill){
        abstractDAO.insert(bill);
    }
}
