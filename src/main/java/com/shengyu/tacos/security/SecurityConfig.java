package com.shengyu.tacos.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
        throws Exception{
            auth.inMemoryAuthentication()
                    .withUser("buzz")
                        .password("infinity")
                        .authorities("ROLE_USER")
                    .and()
                    .withUser("woody")
                        .password("bUllseye")
                        .authorities("ROLE_USER");
    }
}
