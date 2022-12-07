package com.project.PostgreSQLPractice78.config;

import com.project.PostgreSQLPractice78.mainEntities.AccountInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


public record  AccountDetails(AccountInfo account) implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = String.valueOf(account.getRoleID().getRoleName()).replace("_role", "");

        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return account.getHashPassword();
    }

    @Override
    public String getUsername() {
        return account.getLogin();
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
