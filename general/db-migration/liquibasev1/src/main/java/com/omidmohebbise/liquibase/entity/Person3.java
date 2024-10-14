package com.omidmohebbise.liquibase.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Persons3" ,schema = "hr")
@Data
@NoArgsConstructor
public class Person3 implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;
    private Integer age;
}
