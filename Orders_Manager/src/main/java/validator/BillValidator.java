package validator;

import data.access.AbstractDAO;

public class BillValidator {
    public long getCurrentId() {
        long currentId = AbstractDAO.selectMaxId(0);
        return ++currentId;
    }
}
