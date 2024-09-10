package io.security.springsecuritymaster.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.Builder;

@Builder
public record AccountDto(String username, String password, Collection<GrantedAuthority> authorities) {
}
