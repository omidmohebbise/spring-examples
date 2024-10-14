package com.spdeveloper.cassandra.service;

import com.spdeveloper.cassandra.model.Person;
import com.spdeveloper.cassandra.model.repository.CassandraJpaRepository;
import com.spdeveloper.cassandra.service.dto.PersonDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PersonsService {

    private final CassandraJpaRepository repository;

    public PersonsService(CassandraJpaRepository cassandraJpaRepository) {
        this.repository = cassandraJpaRepository;
    }

    @Transactional
    public Person insert(PersonDTO dto) {
        Person p = new Person(UUID.randomUUID().toString(),
                dto.getName(), dto.getAge());
        return repository.save(p);

    }

    public List<PersonDTO> findAll() {
        List<PersonDTO> persons = new ArrayList<>();
        var result = repository.findAll();
        result.forEach(person -> persons.add(new PersonDTO(person.getName(), person.getAge())));
        return persons;
    }
}
