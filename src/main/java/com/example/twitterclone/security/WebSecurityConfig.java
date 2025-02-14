package com.example.twitterclone.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/register", "/api/posts").permitAll() // Доступ к регистрации и постам открыт всем
                        .requestMatchers("/api/posts/**").hasAnyRole("USER", "ADMIN") // Доступ к постам только для USER и ADMIN
                        .requestMatchers("/api/admin/**").hasRole("ADMIN") // Только ADMIN может получить доступ к этому эндпоинту
                        .requestMatchers("/api/public/**").permitAll() // Публичный доступ к данным
                        .anyRequest().authenticated() // Для остальных запросов необходима аутентификация
                );
        return http.build();
    }
}
