package com.debugd.info.springoauth2.config;

import com.debugd.info.springoauth2.service.CustomoidcUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain
            (HttpSecurity httpSecurity,
             CustomoidcUserService customoidcUserService)
            throws Exception {
        httpSecurity.authorizeHttpRequests(auth ->
                auth.requestMatchers("/")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
        )
                .oauth2Login(oauth2 ->
                        oauth2.userInfoEndpoint(userInfo ->
                                userInfo.oidcUserService(customoidcUserService)
                        )
                                .defaultSuccessUrl("/profile", true)
                );
        return httpSecurity.build();
    }
}
