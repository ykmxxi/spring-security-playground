package io.security.springsecuritymaster.config;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        AccountDto account = AccountDto.builder()
                .username("user")
                .password("{noop}1234")
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_USER")))
                .build();

        return new CustomUserDetails(account);
        /*
        return User.withUsername("user")
                .password("{noop}1234")
                .authorities("USER")
                .build();
        */
    }

}
