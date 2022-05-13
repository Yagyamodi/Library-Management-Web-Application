package com.school.work.dao;

import com.school.work.models.Member__emails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberEmailsRowMapper implements RowMapper<Member__emails> {

    @Override
    public Member__emails mapRow(ResultSet rs, int i) throws SQLException {
        Member__emails e = new Member__emails();
        e.setLibraryId(rs.getInt("LibraryId"));
        e.setEmailAddress(rs.getString("EmailAddress"));

        return e;
    }
}
