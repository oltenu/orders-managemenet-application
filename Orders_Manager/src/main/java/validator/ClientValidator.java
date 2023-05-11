package validator;

import data.access.AbstractDAO;
import model.ClientM;

public class ClientValidator {
    public boolean validateClient(ClientM clientM) {
        return clientM.getAge() >= 16;
    }

    public long getCurrentId() {
        long currentId = AbstractDAO.selectMaxId(1);
        return ++currentId;
    }
}
