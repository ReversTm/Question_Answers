package com.example.springbootpages.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            try {
                auth
                        .requestMatchers("/").hasAnyRole("EMPLOYEE", "HR", "MANAGER")
                        .requestMatchers("/hr_info").hasRole("HR")
                        .requestMatchers("/manager_info/**").hasRole("MANAGER")
                        .and().formLogin().permitAll();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}