package com.school.work.dao;

import com.school.work.models.Issues;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IssuesRowMapper implements RowMapper<Issues> {
    @Override
    public Issues mapRow(ResultSet rs, int i) throws SQLException {
        Issues e = new Issues();
        e.setIssueId(rs.getInt("IssueId"));
        e.setLibraryId(rs.getInt("LibraryId"));
        e.setBookId(rs.getString("BookId"));
        e.setIssueDate(rs.getDate("IssueDate"));
        e.setDueDate(rs.getDate("DueDate"));
        e.setWarning(rs.getInt("Warning"));
        e.setCurrentDelay(rs.getInt("CurrentDelay"));
        e.setPhoneNumber(rs.getString("PhoneNumber"));
        e.setReturnDate(rs.getDate("ReturnDate"));
        e.setFine(rs.getInt("Fine"));
        e.setRemarks(rs.getString("Remarks"));
        return e;
    }
}
