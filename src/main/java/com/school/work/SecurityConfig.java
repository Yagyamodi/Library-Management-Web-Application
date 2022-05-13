package com.school.work;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                // admin "/employees/{id}", "/employees/{id}/edit", "/employees/new","/employees/search", "/employees", "/withdrawals/new", "/withdrawals/delete/{id}"
                // employee, admin "/books/new", "/books/purchaseAdd/{id}", "/books/purchase/new", "/subjects/new", "/subjects/{id}/edit", "/issues/**", "/members"
                // employee, admin "/suppliers/**", "/purchases/**", "/withdrawals"
                // all "/books/search","/books/{id}","/books", "/subjects/all", "/subjects/{id}", "/", "/home"
                .antMatchers("/books", "/books/search", "/subjects/all").permitAll()
                .antMatchers("/","/home","/style/**","/employees").permitAll()
                .antMatchers("/books/{^[0-9]+$}", "/books/new", "/books/purchaseAdd/{id}", "/books/purchase/new","/issues/**", "/members/**","/suppliers/**", "/purchases/**").hasAnyAuthority("ROLE_ADMIN","ROLE_EMPLOYEE")
                .antMatchers("/withdrawals","/employees","/employees/{^[0-9]+$}","/credentials/change", "/credentials/{^[a-zA-Z0-9]+$}/change","/subjects/new", "/subjects/{^[0-9]+$}", "/subjects/{id}/edit").hasAnyAuthority("ROLE_ADMIN","ROLE_EMPLOYEE")
                .antMatchers("/credentials/**","/employees/search", "/employees/new","/employees/{^[0-9]+$}/edit", "/withdrawals/new", "/withdrawals/delete/{^[0-9]+$}").hasAnyAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
