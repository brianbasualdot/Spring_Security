package com.org.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Option 1
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests()
                    .requestMatchers("/v1/index2").permitAll()
                    .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                // .httpBasic()
                // .and()
                .build();
    }



        // Option 2 //

 /*
      @Bean
      public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
          return httpSecurity
                 .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/v1/index2").permitAll();
                   auth.anyRequest().authenticated();
                })
                .formLogin()
                   .successHandler(successHandler())
                   .permitAll()
               .and()
               .sessionManagement()
                   .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                    .invalidSessionUrl("/login")
                   .maximumSessions(1)
                   .expiredUrl("/login")
                   .sessionRegistry(sessionRegistry())
                .and()
               .sessionFixation()
                   .migrateSession() // migrateSession - newSesion
                .and()
               .build();
    } */
    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }

     public AuthenticationSuccessHandler successHandler(){
        return (((request, response, authentication) -> {
            response.sendRedirect("/v1/index");
        }));
     }
}