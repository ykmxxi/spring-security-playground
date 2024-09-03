package io.security.springsecuritymaster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("logoutSuccess").permitAll()
                        .anyRequest().authenticated())
//                .csrf(csrf -> csrf.disable()) // GET 방식 로그아웃을 사용하려면 csrf 기능 off
                .formLogin(Customizer.withDefaults())
                .logout(logout -> logout
                        .logoutUrl("/logout") // 기본 POST 방식
                        // RequestMatcher가 logoutUrl()보다 높은 우선권
                        // GET 방식 로그아웃을 허용하려면 "GET" 지정 or HTTP 메서드를 지정하지 않으면 된다
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
                        .logoutSuccessUrl("/logoutSuccess")
                        // LogoutSuccessHandler가 logoutSuccessUrl()보다 높은 우선권
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.sendRedirect("logoutSuccess");
                        })
                        .deleteCookies("JSESSIONID", "remember-me")
                        .invalidateHttpSession(true) // HttpSession 무효화
                        .clearAuthentication(true) // SecurityContextLogoutHandler가 인증을 삭제
                        .addLogoutHandler((request, response, authentication) -> {
                            HttpSession session = request.getSession();
                            session.invalidate();
                            SecurityContextHolderStrategy securityContextHolder = SecurityContextHolder.getContextHolderStrategy();
                            securityContextHolder.getContext()
                                    .setAuthentication(null);
                            securityContextHolder.clearContext();
                        })
                        .permitAll()
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
