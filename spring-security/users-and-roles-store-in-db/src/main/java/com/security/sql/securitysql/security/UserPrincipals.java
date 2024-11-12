package com.security.sql.securitysql.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@AllArgsConstructor
public class UserPrincipals implements UserDetails {

    private String username;
    private String password;
    private List<String> roles ;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(roles == null){
            return Collections.emptySet();
        }else{
            Set<SimpleGrantedAuthority>grantedAuthorities = new HashSet();
            roles.forEach(role->{
                grantedAuthorities.add(new SimpleGrantedAuthority(role));
            });
            return grantedAuthorities;
        }

    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
