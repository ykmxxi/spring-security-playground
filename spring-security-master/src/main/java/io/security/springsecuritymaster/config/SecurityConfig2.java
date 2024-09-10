package io.security.springsecuritymaster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;
/*
@Slf4j
//@EnableWebSecurity
//@Configuration
public class SecurityConfig2 {

    // AuthenticationProvider 1개의 빈만 생성
//    @Bean
    public SecurityFilterChain securityFilterChain(
            final HttpSecurity http,
            final AuthenticationManagerBuilder builder,
            final AuthenticationConfiguration configuration
    ) throws Exception {
        // 자식 AuthenticationManagerBuilder 와 ProviderManager
        AuthenticationManagerBuilder managerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        managerBuilder.authenticationProvider(customAuthenticationProvider());

        // 부모 AuthenticationManagerBuilder 와 ProviderManager
        ProviderManager providerManager = (ProviderManager) configuration.getAuthenticationManager(); // 부모 parent
        providerManager.getProviders().removeFirst(); // 등록한 CustomAuthenticationProvider 삭제
        builder.authenticationProvider(new DaoAuthenticationProvider()); // 부모 빌더에 있는 부모 parent 에 추가한다

        http.authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

//    @Bean
    public AuthenticationProvider customAuthenticationProvider() {
        // 1개의 AuthenticationProvider만 빈으로 등록해 생성
        // 1개만 생성하면 부모 ProviderManager 에서 DaoAuthenticationProvider를 대체
        return new CustomAuthenticationProvider();
    }

    //Security Property 코드 설정
//    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}1234")
                .authorities("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

}
*/
