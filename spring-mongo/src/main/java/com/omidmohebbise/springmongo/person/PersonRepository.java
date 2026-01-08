package com.omidmohebbise.springmongo.person;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {

    List<Person> findByLastNameIgnoreCase(String lastName);
}

