package com.school.work.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.work.models.Userinfo;

import org.springframework.jdbc.core.RowMapper;

public class UserinfoRowMapper implements RowMapper<Userinfo>{

    @Override
    public Userinfo mapRow(ResultSet rs, int row) throws SQLException{
        Userinfo e = new Userinfo();
        e.setUserName(rs.getString("username"));
        e.setPassword(rs.getString("password"));
        e.setEmployeeId(rs.getInt("employeeId"));
        e.setRole(rs.getString("role"));
        return e;
    }
}