package io.security.springsecuritymaster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated()) // http 요청에 대한 인가 설정
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

    // Security Property 코드 설정
    // org.springframework.security.core.userdetails.User & UserDetails
//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        UserDetails user = User.withUsername("user")
//                .password("1234")
//                .authorities("ROLE_USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }

}
