package io.security.springsecuritymaster.config;

import java.util.List;

import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// AuthenticationManager 직접 등록 사용
//@EnableWebSecurity
//@Configuration
public class SecurityConfig2 {

//    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/api/login").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(customFilter(http), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    public CustomAuthenticationFilter customFilter(final HttpSecurity http) {
        List<AuthenticationProvider> list1 = List.of(new DaoAuthenticationProvider());
        ProviderManager parent = new ProviderManager(list1);

        List<AuthenticationProvider> list2 = List.of(new AnonymousAuthenticationProvider("key"),
                new CustomAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(list2, parent);

        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(http);
        customAuthenticationFilter.setAuthenticationManager(authenticationManager);

        return customAuthenticationFilter;

    }

//    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user").password("{noop}1111").roles("USER").build();
        return new InMemoryUserDetailsManager(user);
    }

}
