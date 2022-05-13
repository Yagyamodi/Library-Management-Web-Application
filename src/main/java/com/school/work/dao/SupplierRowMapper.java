package com.school.work.dao;

import com.school.work.models.Supplier;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierRowMapper implements RowMapper<Supplier> {
    @Override
    public Supplier mapRow(ResultSet rs, int i) throws SQLException {
        Supplier e = new Supplier();
        e.setSupplierId(rs.getString("SupplierId"));
        e.setSupplierName(rs.getString("SupplierName"));
        e.setShopName(rs.getString("ShopName"));
        e.setCatalog(rs.getString("Catalog"));
        e.setShopLocation(rs.getString("ShopLocation"));
        e.setShopCity(rs.getString("ShopCity"));
        e.setShopState(rs.getString("ShopState"));
        e.setShopPincode(rs.getInt("ShopPincode"));
        e.setPhoneNumber(rs.getString("PhoneNumber"));
        e.setEmailAddress(rs.getString("EmailAddress"));

        return e;
    }
}
