package com.omidmohebbise.liquibase.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "Departments" ,schema = "hr" ,catalog = "hr")
@Data
@NoArgsConstructor
public class Department  implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String title;
}
