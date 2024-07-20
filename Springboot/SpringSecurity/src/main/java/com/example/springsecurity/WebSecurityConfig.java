package com.example.springsecurity;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurationA {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf(Customizer.withDefaults()).disable()
                .authorizeRequests()
                .antMatchers("/users").hasRole("USER")
                .anyRequest().authenticated();
    }
}
