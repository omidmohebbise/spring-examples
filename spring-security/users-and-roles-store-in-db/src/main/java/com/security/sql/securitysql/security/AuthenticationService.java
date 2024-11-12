package com.security.sql.securitysql.security;

import com.security.sql.securitysql.security.dto.JwtAuthenticationResponse;
import com.security.sql.securitysql.security.dto.SignInRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse signIn(SignInRequest request);
}
