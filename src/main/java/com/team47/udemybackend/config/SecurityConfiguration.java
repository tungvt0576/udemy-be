package com.team47.udemybackend.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.team47.udemybackend.user.Permission.*;
import static com.team47.udemybackend.user.Role.ADMIN;
import static com.team47.udemybackend.user.Role.USER;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("api/v1/auth/**", "/swagger-ui/**", "/v3/api-docs/**")
                        .permitAll()
                        .requestMatchers(GET, "api/v1/course/**").permitAll()
                        .requestMatchers("api/v1/course/**").hasAnyRole(ADMIN.name(), USER.name())

                        .requestMatchers("api/v1/enroll/**").hasAnyRole(ADMIN.name(), USER.name())
                        .requestMatchers("api/v1/enroll/**").hasAnyAuthority(USER_CREATE.name(), ADMIN_CREATE.name())
                        .requestMatchers("api/v1/enroll/**").hasAnyAuthority(USER_READ.name(), ADMIN_READ.name())
                        .requestMatchers("api/v1/enroll/**").hasAnyAuthority(ADMIN_DELETE.name())

                        .requestMatchers("/api/v1/user/**").hasAnyRole(ADMIN.name(), USER.name())

                        .requestMatchers(GET, "/api/v1/user/**").hasAnyAuthority(ADMIN_READ.name(), USER_READ.name())
                        .requestMatchers(POST, "/api/v1/user/**").hasAnyAuthority(ADMIN_CREATE.name(), USER_CREATE.name())
                        .requestMatchers(PUT, "/api/v1/user/**").hasAnyAuthority(ADMIN_UPDATE.name(), USER_UPDATE.name())
                        .requestMatchers(DELETE, "/api/v1/user/**").hasAnyAuthority(ADMIN_DELETE.name())

                        .requestMatchers("/api/v1/admin/**").hasRole(ADMIN.name())
                        .requestMatchers(GET, "/api/v1/admin/**").hasAuthority(ADMIN_READ.name())
                        .requestMatchers(POST, "/api/v1/admin/**").hasAuthority(ADMIN_CREATE.name())
                        .requestMatchers(PUT, "/api/v1/admin/**").hasAuthority(ADMIN_UPDATE.name())
                        .requestMatchers(DELETE, "/api/v1/admin/**").hasAuthority(ADMIN_DELETE.name())

                        .anyRequest()
                        .authenticated())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/api/v1/auth/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()));
//                .httpBasic(Customizer.withDefaults())

        return http.build();
    }
}

