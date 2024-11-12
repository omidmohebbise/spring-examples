package com.security.sql.securitysql.repository;

import com.security.sql.securitysql.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByTitle(String username);

}
