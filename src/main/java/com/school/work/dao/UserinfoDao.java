package com.school.work.dao;

import java.util.List;

import com.school.work.models.Userinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class UserinfoDao{
    private JdbcTemplate template;

    @Autowired
    public void setTemplate(JdbcTemplate template){
        this.template = template;
    }

    public Userinfo getUserinfoByUserName(String username){
        String sql = "select * from userinfo where username=?";
        return template.queryForObject(sql,
        new Object[]{username},
        new UserinfoRowMapper());
    }


    public String save(Userinfo cls){
        String sql = "insert into userinfo (username,password,employeeId,role) values ('" + cls.getUserName() + "','" + cls.getPassword() + "'," + cls.getEmployeeId() + ",'" + cls.getRole() + "')";

        template.update(sql);
        return cls.getUserName();
    }

    public void update(Userinfo cls, String prevUserName){
        String sql = "update userinfo set username = '" + cls.getUserName() + "',password='" + cls.getPassword() + "',employeeId = " + cls.getEmployeeId()  + ",role = '" + cls.getRole() + "' where username = '" + prevUserName+"'";
        template.update(sql);
    }

    public Userinfo getUserinfoByEmployeeId(int employeeId){
        String sql = "select * from userinfo where employeeId=?";
        return template.queryForObject(sql,
        new Object[]{employeeId},
        new UserinfoRowMapper());
    }

    public List<Userinfo> getAllUserinfo() {
        String sql="select * from userinfo";
        return template.query(sql,
        new UserinfoRowMapper());
    }

    public boolean isEmployeeWithUsernameExists(String username){
        String sql = "select count(*) from userinfo where username=?";
        int count = template.queryForObject(sql, new Object[]{username},Integer.class);
        return (count>0)?true:false;
    }

}