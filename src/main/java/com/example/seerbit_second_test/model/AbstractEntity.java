package com.example.seerbit_second_test.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@MappedSuperclass
@Data
public class AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    protected String delFlag = "N";
    private Date createdOn;

    @PrePersist
    void onCreate(){
        if(createdOn == null)
            createdOn = new Date();
    }
}
