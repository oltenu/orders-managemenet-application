package model;

import lombok.Data;

@Data
public class Client {
    private long clientId;
    private String firstName;
    private String lastName;
    private int age;
    private String address;
    private String cnp;

    public Client(long clientId, String firstName, String lastName, int age, String address, String cnp) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.cnp = cnp;
    }


}
