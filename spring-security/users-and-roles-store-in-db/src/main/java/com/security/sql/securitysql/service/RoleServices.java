package com.security.sql.securitysql.service;

import com.security.sql.securitysql.model.Role;
import com.security.sql.securitysql.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServices  {
    private final RoleRepository repository;

    public void initRoles(){
        if(repository.count()==0){
            repository.save(new Role( "ROLE_ADMIN", null));
            repository.save(new Role("ROLE_MANAGER", null));
        }
    }
}
