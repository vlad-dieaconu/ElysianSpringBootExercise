package Elysian.SpringBootExercise.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true
)

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        /**Password = password
         switch between roles to check hasRole from controllers
         * **/
        auth.inMemoryAuthentication().withUser("admin").password("$2a$10$4xnpk2a5jLr1mf6VWle6Vuv4q7DBsW2rqQcg6N1Ms/y4g98Ry4D4C").roles("ADMIN");
        //auth.inMemoryAuthentication().withUser("manager").password("$2a$10$4xnpk2a5jLr1mf6VWle6Vuv4q7DBsW2rqQcg6N1Ms/y4g98Ry4D4C").roles("MANAGER");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/product/**").permitAll()
                .antMatchers("/api/section/**").permitAll()
                .antMatchers("/api/store/**").permitAll()
                .anyRequest().authenticated();

        http.cors().and().csrf().disable();
        http.httpBasic();

    }

}