package com.security.sql.securitysql.security.impl;

import com.security.sql.securitysql.model.Role;
import com.security.sql.securitysql.repository.UserRepository;
import com.security.sql.securitysql.security.AuthenticationService;
import com.security.sql.securitysql.security.JwtService;
import com.security.sql.securitysql.security.UserPrincipals;
import com.security.sql.securitysql.security.dto.JwtAuthenticationResponse;
import com.security.sql.securitysql.security.dto.SignUpRequest;
import com.security.sql.securitysql.security.dto.SignInRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        return null;
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByUsername(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var userPrincipals = new UserPrincipals(user.getUsername(), user.getPassword(), user.getRoles().stream().map(Role::getTitle).toList());
        var jwt = jwtService.generateToken(userPrincipals);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
