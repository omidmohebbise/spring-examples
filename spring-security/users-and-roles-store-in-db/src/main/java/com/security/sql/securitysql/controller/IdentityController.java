package com.security.sql.securitysql.controller;

import com.security.sql.securitysql.security.AuthenticationService;
import com.security.sql.securitysql.security.dto.JwtAuthenticationResponse;
import com.security.sql.securitysql.security.dto.SignInRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class IdentityController {

    private final AuthenticationService authenticationService;

    @GetMapping("/api/unsecured")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/api/secured")
    @SecurityRequirement(name = "Bearer Authentication")
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public String securedRest() {
        return "I am secured rest";
    }

    @GetMapping("/api/secured-manager")
    @SecurityRequirement(name = "Bearer Authentication")
    @Secured({"ROLE_MANAGER"})
    public String securedForAdmin() {
        return "I am secured rest for managers";
    }

    @GetMapping("/api/secured-admin")
    @SecurityRequirement(name = "Bearer Authentication")
    @Secured({"ROLE_ADMIN"})
    public String securedForManager() {
        return "I am secured rest for admins";
    }

    @PostMapping("/auth/sign-in")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }

}

