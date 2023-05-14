package validator;

import data.access.AbstractDAO;

/**
 * It is a validator class for a bill object, it also generates an ID for a new object.
 */
public class BillValidator {
    public long getCurrentId() {
        long currentId = AbstractDAO.selectMaxId(0);
        return ++currentId;
    }
}
