package com.school.work.dao;

import com.school.work.models.Withdrawal;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WithdrawalRowMapper implements RowMapper<Withdrawal> {
    @Override
    public Withdrawal mapRow(ResultSet rs, int i) throws SQLException {
        Withdrawal e = new Withdrawal();
        e.setWithdrawalId(rs.getString("WithdrawalId"));
        e.setWithdrawalDate(rs.getDate("WithdrawalDate"));
        e.setBookId(rs.getString("BookId"));
        e.setReason(rs.getString("Reason"));
        e.setBillNumber(rs.getString("BillNumber"));

        return e;
    }
}
