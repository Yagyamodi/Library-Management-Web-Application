package com.school.work.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;

import com.school.work.models.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class EmployeeDao{
    private JdbcTemplate template;

    @Autowired
    public void setTemplate(JdbcTemplate template){
        this.template = template;
    }

    public Employee getEmployeeByEmployeeId(int employeeId){
        String sql = "select * from employee where employeeId=?";
        return template.queryForObject(sql,
        new Object[]{employeeId},
        new EmployeeRowMapper());
    }

    public int save(Employee emp){
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(emp.getDOB());

        String sql = "insert into employee (Fname,Lname,gender,DOB,address,contact,designation,email) values ('" +
        emp.getFname() + "','" + emp.getLname() + "','" + emp.getGender() + "','" + date + "','" + emp.getAddress() +
        "','" + emp.getContact() + "','" + emp.getDesignation() + "','" + emp.getEmail() + "')";

        System.out.println("EmployeeDao save: " + sql);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(
            new PreparedStatementCreator(){
            
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                    return ps;
                }
            },keyHolder
        );
        return keyHolder.getKey().intValue();
    }

    public void update(Employee emp){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(emp.getDOB());

        String sql = "update employee set Fname='" + emp.getFname() + "', Lname='" + emp.getLname() + "', gender='" + emp.getGender() +
        "', address='" + emp.getAddress() + "', contact='" + emp.getContact() + "', DOB='" + date + "', designation='" + emp.getDesignation() + "', email='" +
        emp.getEmail() +  "' where employeeId = " + emp.getEmployeeId();
        System.out.println(sql);
        template.update(sql);
    }

    public List<Employee > getAllEmployees(){
        String sql="select * from employee";
        return template.query(sql,
        new EmployeeRowMapper());
    }

    public boolean isEmployeeIdPresent(String employeeId){
        String sql = "select count(*) from employee where employeeId=?";
        int count = template.queryForObject(sql, new Object[]{employeeId},Integer.class);
        return (count>0)?true:false;
    }

}