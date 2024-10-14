package com.omidmohebbise.liquibase.repository;


import com.omidmohebbise.liquibase.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonJpaRepository extends JpaRepository<Person,Long>, JpaSpecificationExecutor {

}
