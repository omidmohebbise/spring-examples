package com.omidmohebbise.aspect.service;

import com.omidmohebbise.aspect.domain.Person;
import com.omidmohebbise.aspect.domain.repository.PersonJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private final PersonJpaRepository repository;

    public PersonService(PersonJpaRepository repository) {
        this.repository = repository;
    }

    public Person addPerson(String name , String family){
        return repository.save(Person.factory(name, family));
    }
}
