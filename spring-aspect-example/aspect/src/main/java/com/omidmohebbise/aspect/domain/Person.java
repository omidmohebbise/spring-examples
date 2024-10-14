package com.omidmohebbise.aspect.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "persons")
@Getter
@Setter
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
    public String family;


    public static Person factory(String name, String family) {
        Person p = new Person();
        p.setName(name);
        p.setFamily(family);
        return p;
    }

}
