package com.security.sql.securitysql.repository;

import com.security.sql.securitysql.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User , Long> {
    Optional<User> findByUsername(String username);
}
