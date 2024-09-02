package io.security.springsecuritymaster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        // 익명 사용자를 위한 권한 설정
                        .requestMatchers("/anonymous").hasRole("GUEST")
                        .requestMatchers("/anonymousContext", "/authentication").permitAll()
                        .anyRequest().authenticated()
                ).formLogin(Customizer.withDefaults())
                // 익명 인증 사용자는 보통 withDefaults() 기본 설정 사용
                .anonymous(anonymous -> anonymous
                        .principal("guest")
                        .authorities("ROLE_GUEST")
                );

        return http.build();
    }

    //Security Property 코드 설정
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}1234")
                .authorities("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

}
