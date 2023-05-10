package model;

import lombok.Data;

@Data
public class Client {
    private long id;
    private String firstName;
    private String lastName;
    private int age;
    private String address;
    private String cnp;

    public Client() {
    }

    public Client(long id, String firstName, String lastName, int age, String address, String cnp) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.cnp = cnp;
    }


}
