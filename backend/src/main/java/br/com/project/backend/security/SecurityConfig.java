package br.com.project.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.csrf((csrf) -> csrf.disable()).authorizeHttpRequests((requests) -> requests
                .requestMatchers(HttpMethod.GET, "/token").permitAll()
                .requestMatchers(HttpMethod.POST, "/users").permitAll()
                .requestMatchers(HttpMethod.GET, "/users").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/users/{id}").permitAll()
                .requestMatchers(HttpMethod.PUT, "/users").permitAll()
                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/teste-token").permitAll()
                .requestMatchers(HttpMethod.POST, "/request-reset-password").permitAll()
                .requestMatchers(HttpMethod.POST, "/confirm-reset-password").permitAll()
                .anyRequest().authenticated());

        http.addFilterBefore(new SecurityFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
