package com.omidmohebbise.springbatch.service;

import com.omidmohebbise.springbatch.domain.Person;
import com.omidmohebbise.springbatch.domain.PersonJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonJpaRepository repository;

    public PersonService(PersonJpaRepository repository) {
        this.repository = repository;
    }

    public Person add(String name, String family){
        var person  = new Person();
        person.setFirstName(name);
        person.setLastName(family);
        return repository.save(person);
    }

    public List<Person> saveAll(List<Person> persons){
        return repository.saveAll(persons);
    }
}
