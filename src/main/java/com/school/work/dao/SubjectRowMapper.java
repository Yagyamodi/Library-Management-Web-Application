package com.school.work.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.work.models.Subject;

import org.springframework.jdbc.core.RowMapper;

public class SubjectRowMapper implements RowMapper<Subject>{

    @Override
    public Subject mapRow(ResultSet rs, int row) throws SQLException {
        Subject e = new Subject();
        e.setSubjectId(rs.getInt("SubjectId"));
        e.setSubjectNumber(rs.getString("SubjectNumber"));
        e.setSubjectName(rs.getString("SubjectName"));
        return e;
    }
}