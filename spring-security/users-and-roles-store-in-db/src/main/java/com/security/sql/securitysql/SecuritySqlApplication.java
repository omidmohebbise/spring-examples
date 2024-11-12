package com.security.sql.securitysql;

import com.security.sql.securitysql.service.RoleServices;
import com.security.sql.securitysql.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@SpringBootApplication
@RequiredArgsConstructor
@Service
public class SecuritySqlApplication implements CommandLineRunner {
    private final UserService userService;
    private final RoleServices roleServices;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SecuritySqlApplication.class, args);
    }


    @Override
    public void run(String... args) {
        roleServices.initRoles();
        userService.initUsers(passwordEncoder.encode("123"));
    }
}
