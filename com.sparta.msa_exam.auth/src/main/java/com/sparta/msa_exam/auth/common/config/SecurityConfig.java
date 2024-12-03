package com.sparta.msa_exam.auth.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.sparta.msa_exam.auth.domain.user.service.MsaUserDetailService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MsaUserDetailService msaUserDetailService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http.httpBasic(AbstractHttpConfigurer::disable)
           .csrf(AbstractHttpConfigurer::disable)
           .formLogin(AbstractHttpConfigurer::disable)
           .cors(AbstractHttpConfigurer::disable)
           .authorizeRequests(authorize -> authorize
                .requestMatchers("/auth/sign-in").permitAll()
                .requestMatchers("/auth/sign-up").permitAll()
                .requestMatchers("/auth/reissue").permitAll()
                .anyRequest().authenticated()
            )
           .userDetailsService(msaUserDetailService)

            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}