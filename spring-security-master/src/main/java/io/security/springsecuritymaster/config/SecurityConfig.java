package io.security.springsecuritymaster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated()) // http 요청에 대한 인가 설정
                .formLogin(form -> form
//                        .loginPage("/loginPage")
                                .loginProcessingUrl("/loginProc")
                                .defaultSuccessUrl("/", true)
                                .failureUrl("/failed")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .successHandler((req, res, auth) -> {
                                    // AuthenticationSuccessHandler.onAuthenticationSuccess() 구현
                                    log.info("authentication: {}", auth);
                                    res.sendRedirect("/home");
                                })
                                .failureHandler((req, res, e) -> {
                                    // AuthenticationFailureHandler.onAuthenticationFailure() 구현
                                    log.info("exception: {}", e.getMessage());
                                    res.sendRedirect("/login");
                                })
                                .permitAll()
                );

        return http.build();
    }

    //Security Property 코드 설정
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails user = User.withUsername("user")
                .password("{noop}1234")
                .authorities("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

}
