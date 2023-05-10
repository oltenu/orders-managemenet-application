package logic;

import data.access.ClientDAO;
import model.Client;
import validator.ClientValidator;

import java.util.List;

public class ClientBLL {
    private final ClientValidator clientValidator;
    private final ClientDAO clientDAO;

    public ClientBLL(){
        clientValidator = new ClientValidator();
        clientDAO = new ClientDAO();
    }

    public boolean insertClient(String firstName, String lastName, int age, String address, String cnp){
        Client client = new Client(clientValidator.getCurrentId(), firstName, lastName, age, address, cnp);
        if(!clientValidator.validateClient(client))
            return false;

        clientDAO.insert(client);

        return true;
    }

    public boolean updateClient(long clientId, String firstName, String lastName, int age, String address, String cnp){
        Client clientToUpdate = createClient(clientId, firstName, lastName, age, address, cnp);
        if(!clientValidator.validateClient(clientToUpdate))
            return false;

        clientDAO.insert(clientToUpdate);

        return true;
    }

    public void deleteClient(long id){
        clientDAO.delete(id);
    }

    public Client findClient(long id){
        return clientDAO.findById(id);
    }

    public List<Client> findAllClients(){
        return clientDAO.findAll();
    }

    private Client createClient(long id, String firstName, String lastName, int age, String address, String cnp){
        return new Client(id, firstName, lastName, age, address, cnp);
    }
}
