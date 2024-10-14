package com.omidmohebbise.aspect.domain.repository;

import com.omidmohebbise.aspect.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonJpaRepository extends JpaRepository<Person, Long> {
}
