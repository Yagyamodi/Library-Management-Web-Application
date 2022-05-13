package com.school.work.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.work.models.Purchase;

import org.springframework.jdbc.core.RowMapper;

public class PurchaseRowMapper implements RowMapper<Purchase>{

    @Override
    public Purchase mapRow(ResultSet rs, int i) throws SQLException {
        Purchase e = new Purchase();
        e.setBookId(rs.getString("BookId"));
        e.setBillNumber(rs.getString("BillNumber"));
        e.setPurchaseYear(rs.getInt("PurchaseYear"));
        e.setCost(rs.getInt("Cost"));
        e.setSupplierId(rs.getString("SupplierId"));
        return e;
    }
}
