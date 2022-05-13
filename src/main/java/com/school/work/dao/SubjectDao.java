package com.school.work.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.school.work.models.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class SubjectDao{
    private JdbcTemplate template;

    @Autowired
    public void setTemplate(JdbcTemplate template){
        this.template = template;
    }

    public Subject getSubjectBySubjectNumber(String subjectNumber){
        String sql = "select * from subject where SubjectNumber=" + subjectNumber;
        System.out.println("Here in subjectDao getSubjectbySubjectNumber");

        return template.queryForObject(sql,
                new SubjectRowMapper());
    }

    public Subject getSubjectBySubjectId(int subjectId){
        String sql = "select * from subject where SubjectId=" + subjectId;
        System.out.println("Here in subjectDao getSubjectBySubjectId");

        return template.queryForObject(sql,
                new SubjectRowMapper());
    }

    public List<Subject> getAllSubjects(){
        String sql="select * from subject";
        return template.query(sql,
                new SubjectRowMapper());
    }

    public int save(Subject sub){
        String sql = "insert into subject (SubjectNumber, SubjectName) values ('" + sub.getSubjectNumber() + "','" + sub.getSubjectName() + "')";

        System.out.println("In SubjectDao save method 1");

        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(
                new PreparedStatementCreator(){

                    @Override
                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                        return ps;
                    }
                },keyHolder
        );

        return keyHolder.getKey().intValue();
    }

    public void update(Subject sub){
        String sql = "update subject set SubjectName = '" + sub.getSubjectName() + "', SubjectNumber= '" + sub.getSubjectNumber() + "' where SubjectId = " + sub.getSubjectId();
        System.out.println("Inside SubjectDao, update method");
        template.update(sql);
    }

}