package com.spdeveloper.cassandra.model.repository;

import com.spdeveloper.cassandra.model.Person;
import org.springframework.data.repository.CrudRepository;


public interface CassandraJpaRepository extends CrudRepository<Person,String> {
}
