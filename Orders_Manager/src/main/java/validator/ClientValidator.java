package validator;

import model.Client;

public class ClientValidator {
    private long currentId = -1;

    public boolean validateClient(Client client){
        return client.getAge() >= 16;
    }

    public long getCurrentId(){
        return currentId++;
    }
}
