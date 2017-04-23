package se.torgammelgard.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureItAll(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("u").password("p").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //.anyRequest().permitAll() // TODO change this
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .csrf().disable();
    }
}
