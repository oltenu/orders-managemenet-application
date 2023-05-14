package logic;

import data.access.ClientDAO;
import model.ClientM;
import validator.ClientValidator;

import java.util.List;

/**
 * Business logic class of ClientM class.
 * Contains a validator and a data access object.
 */
public class ClientBLL {
    private final ClientValidator clientValidator;
    private final ClientDAO clientDAO;

    public ClientBLL() {
        clientValidator = new ClientValidator();
        clientDAO = new ClientDAO();
    }

    public boolean insertClient(String firstName, String lastName, int age, String address, String cnp) {
        ClientM clientM = new ClientM(clientValidator.getCurrentId(), firstName, lastName, age, address, cnp);
        if (!clientValidator.validateClient(clientM))
            return false;

        clientDAO.insert(clientM);

        return true;
    }

    public boolean updateClient(long clientId, String firstName, String lastName, int age, String address, String cnp) {
        ClientM clientMToUpdate = createClient(clientId, firstName, lastName, age, address, cnp);
        if (!clientValidator.validateClient(clientMToUpdate))
            return false;

        clientDAO.update(clientMToUpdate);

        return true;
    }

    public void deleteClient(long id) {
        clientDAO.delete(id);
    }

    public ClientM findClient(long id) {
        return clientDAO.findById(id);
    }

    public List<ClientM> findAllClients() {
        return clientDAO.findAll();
    }

    private ClientM createClient(long id, String firstName, String lastName, int age, String address, String cnp) {
        return new ClientM(id, firstName, lastName, age, address, cnp);
    }
}
