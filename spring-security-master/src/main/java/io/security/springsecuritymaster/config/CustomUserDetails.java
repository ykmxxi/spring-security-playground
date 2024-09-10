package io.security.springsecuritymaster.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final AccountDto account;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return account.authorities();
    }

    @Override
    public String getPassword() {
        return account.password();
    }

    @Override
    public String getUsername() {
        return account.username();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired(); // true
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked(); // true
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired(); // true
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled(); // true
    }

}
