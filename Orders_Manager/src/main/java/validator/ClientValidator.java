package validator;

import lombok.Data;
import model.Client;

@Data
public class ClientValidator {
    private long currentId = -1;

    public boolean validateClient(Client client){
        return client.getAge() >= 16;
    }

    public long getCurrentId(){
        return currentId++;
    }
}
