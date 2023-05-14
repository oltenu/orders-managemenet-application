package validator;

import data.access.AbstractDAO;
import model.ClientM;

/**
 * It is a validator class for a client object, it also generates an ID for a new object.
 */
public class ClientValidator {
    public boolean validateClient(ClientM clientM) {
        return clientM.getAge() >= 16;
    }

    public long getCurrentId() {
        long currentId = AbstractDAO.selectMaxId(1);
        return ++currentId;
    }
}
