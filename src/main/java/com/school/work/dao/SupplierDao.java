package com.school.work.dao;

import com.school.work.models.Issues;
import com.school.work.models.Purchase;
import com.school.work.models.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class SupplierDao {
    private JdbcTemplate template;
    private int id = 4;

    @Autowired
    public void setTemplate(JdbcTemplate template){
        this.template = template;
    }

    public Supplier getSupplierBySupplierId(String supplierId){
        String sql = "select * from supplier where SupplierId='" + supplierId + "'";
        return template.queryForObject(sql,
                new SupplierRowMapper());
    }

    public List<Supplier> getAllSuppliers(){
        String sql = "select * from supplier";
        return template.query(sql,
                new SupplierRowMapper());
    }


    public String save(Supplier supplier){
        id=id+1;

        String sql = "insert into supplier (SupplierId, SupplierName, ShopName, Catalog, ShopLocation, ShopCity,"
                + "ShopState, ShopPincode, PhoneNumber, EmailAddress) values ('S" + id + "','"
                + supplier.getSupplierName() + "','" + supplier.getShopName() + "','" + supplier.getCatalog() + "','"
                + supplier.getShopLocation() + "','" + supplier.getShopCity() + "','" + supplier.getShopState() + "',"
                + supplier.getShopPincode() + ",'" + supplier.getPhoneNumber() + "','" + supplier.getEmailAddress() + "')";

        System.out.println("In SupplierDao save method 1");
        template.update(sql);

        return "S"+id;
    }

    public void update(Supplier supplier){
        String sql = "update supplier set SupplierName = '" + supplier.getSupplierName() + "', ShopName = '" + supplier.getShopName()
                + "', Catalog = '" + supplier.getCatalog() + "', ShopLocation = '" + supplier.getShopLocation()
                + "', ShopCity = '" + supplier.getShopCity() + "', ShopState = '" + supplier.getShopState()
                + "', ShopPincode = " + supplier.getShopPincode() + ", PhoneNumber = '" + supplier.getPhoneNumber()
                + "', EmailAddress = '" + supplier.getEmailAddress() + "' where SupplierId = '" + supplier.getSupplierId() + "'";

        System.out.println("Inside SupplierDao, update method");
        template.update(sql);
    }

}
