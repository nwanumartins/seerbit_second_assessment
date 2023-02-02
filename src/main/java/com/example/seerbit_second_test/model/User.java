package com.example.seerbit_second_test.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class User extends AbstractEntity{

    private String firstName;
    private String middleName;
    private String email;
    private String phoneNumber;
    private String password;
    private String hashPassword;
}
