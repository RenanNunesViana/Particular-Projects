package com.cariocar.angular.cariocarangular.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(value = "User")
@Data
@AllArgsConstructor
public class User {
    @Id
    private ObjectId id;
    private String cpf;
    private String firstName;
    private String lastName;
    private String cel;
    private String email;
    private String role;
    private String password;
    @DBRef
    private List<Car> cars;

    public User(){
        this.cars = new ArrayList<>();
    }

    public User(String cpf, String firstName, String lastName, String cel, String email, String role, String password) {
        this.cpf = cpf;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cel = cel;
        this.email = email;
        this.role = role;
        this.password = password;
        this.cars = new ArrayList<>();
    }
}
