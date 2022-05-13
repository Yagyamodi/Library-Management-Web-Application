package com.school.work;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        System.out.print(username);
        List<Map<String, Object>> roles=jdbcTemplate.queryForList("select role from userinfo where username=?",username);
        
        if (roles.isEmpty()){
            System.out.println("Error: Username not found");
            throw new UsernameNotFoundException(username);
        }
        // System.out.print(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        
        for (Map<String, Object> role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority((String)role.get("role")));
        }
        String password=(String)jdbcTemplate.queryForObject("select password from userinfo where username=?", String.class, username);
        return new User(username, password,grantedAuthorities);
    }
}