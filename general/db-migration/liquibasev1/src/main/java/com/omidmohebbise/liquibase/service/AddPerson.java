package com.omidmohebbise.liquibase.service;

import com.omidmohebbise.liquibase.entity.Person;
import com.omidmohebbise.liquibase.repository.PersonJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AddPerson {

    private PersonJpaRepository personJpaRepository;

    public AddPerson(PersonJpaRepository personJpaRepository) {
        this.personJpaRepository = personJpaRepository;
    }

    public Person add(){
        Integer size = personJpaRepository.findAll().size()+1;
        Person person = new Person();
        person.setFirstname("pname"+size);
        person.setLastname("pfamily"+size);
        person.setAge(50);
        return personJpaRepository.save(person);
    }


}
