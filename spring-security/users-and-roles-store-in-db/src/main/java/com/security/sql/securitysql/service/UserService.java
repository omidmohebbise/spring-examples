package com.security.sql.securitysql.service;

import com.security.sql.securitysql.model.Role;
import com.security.sql.securitysql.model.User;
import com.security.sql.securitysql.repository.RoleRepository;
import com.security.sql.securitysql.repository.UserRepository;
import com.security.sql.securitysql.security.UserPrincipals;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public void initUsers(String defaultPass) {

        if (userRepository.count() == 0) {
            Role adminRole = roleRepository.findByTitle("ROLE_ADMIN");
            Role managerRole = roleRepository.findByTitle("ROLE_MANAGER");
            var user1 = new User("admin", defaultPass);
            userRepository.save(user1);
            var user2 = new User("manager", defaultPass);
            userRepository.save(user2);

            user1.getRoles().add(adminRole);
            user2.getRoles().add(managerRole);

            userRepository.save(user1);
            userRepository.save(user2);

        }
    }
    public UserDetails loadUserByUsername(String username) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserPrincipals(user.getUsername(), user.getPassword(), user.getRoles().stream().map(Role::getTitle).toList());
    }
}
