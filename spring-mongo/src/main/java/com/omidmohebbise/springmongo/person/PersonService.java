package com.omidmohebbise.springmongo.person;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Optional<Person> findById(String id) {
        return personRepository.findById(id);
    }

    public List<Person> findByLastName(String lastName) {
        return personRepository.findByLastNameIgnoreCase(lastName);
    }

    public Person create(String firstName, String lastName, Integer age) {
        return personRepository.save(new Person(firstName, lastName, age));
    }

    public Optional<Person> update(String id, String firstName, String lastName, Integer age) {
        return personRepository.findById(id)
                .map(existing -> {
                    existing.setFirstName(firstName);
                    existing.setLastName(lastName);
                    existing.setAge(age);
                    return personRepository.save(existing);
                });
    }

    public boolean deleteById(String id) {
        if (!personRepository.existsById(id)) {
            return false;
        }
        personRepository.deleteById(id);
        return true;
    }
}
