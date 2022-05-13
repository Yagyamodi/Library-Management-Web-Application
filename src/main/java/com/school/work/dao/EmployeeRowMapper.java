package com.school.work.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.work.models.Employee;

import org.springframework.jdbc.core.RowMapper;

public class EmployeeRowMapper implements RowMapper<Employee>{

    @Override
    public Employee mapRow(ResultSet rs, int row) throws SQLException{
        Employee e = new Employee();
        e.setEmployeeId(rs.getInt("employeeId"));
        e.setFname(rs.getString("Fname"));
        e.setLname(rs.getString("Lname"));
        e.setGender(rs.getString("gender"));
        e.setDOB(rs.getDate("DOb"));
        e.setAddress(rs.getString("address"));
        e.setContact(rs.getString("contact"));
        e.setEmail(rs.getString("email"));
        e.setDesignation(rs.getString("designation"));
        return e;
    }
}

//$2a$10$FLHlXIvpBzKq4AKQ32eqP.3FPHEJRtQNetLCEc.BLIlywzf2EDOR2