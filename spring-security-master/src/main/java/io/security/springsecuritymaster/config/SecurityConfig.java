package io.security.springsecuritymaster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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

    // AuthenticationProvider 일반 객체로 생성
    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        // AuthenticationManagerBuilder를 사용한 AuthenticationProvider 등록
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(
                AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.authenticationProvider(new CustomAuthenticationProvider());
        authenticationManagerBuilder.authenticationProvider(new CustomAuthenticationProvider2());

        http.authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults());
        // HttpSecurity를 사용한 AuthenticationProvider 등록
//                .authenticationProvider(new CustomAuthenticationProvider())
//                .authenticationProvider(new CustomAuthenticationProvider2());
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
