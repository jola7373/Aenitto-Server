package com.firefighter.aenitto.config;


import com.firefighter.aenitto.auth.service.TokenService;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http, OAuth2ResourceServerProperties.Jwt jwt, TokenService tokenService) throws
//            Exception {
//            http
//                .authorizeRequests()
//                .antMatchers(
//                        "/api/v1/auth/**"
//                        ).permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .disable()
//                .csrf()
//                .disable()
//                .headers()
//                .disable()
//                .httpBasic()
//                .disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilterBefore(jwtAuthenticationFilter(jwt, tokenService), UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }

}
