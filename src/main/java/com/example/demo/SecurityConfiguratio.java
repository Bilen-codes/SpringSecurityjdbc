package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;


@Configuration
public class SecurityConfiguratio extends WebSecurityConfigurerAdapter {

    @Bean
    public static BCryptPasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }
    //bring up the form for log in + define who sees the form

    @Override
    protected void configure (HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll();
        httpSecurity.csrf().ignoringAntMatchers("/h2-console/**");
        httpSecurity.headers().frameOptions().sameOrigin();
    }
    @Autowired
    private DataSource d;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().dataSource(d)
                .withDefaultSchema()
                .withUser(User.withUsername("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER"));
    }


}
