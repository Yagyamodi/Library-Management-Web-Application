package com.school.work.dao;

import com.school.work.models.Purchase;
import com.school.work.models.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class PurchaseDao {
    private JdbcTemplate template;

    @Autowired
    public void setTemplate(JdbcTemplate template){
        this.template = template;
    }


    public Purchase getPurchaseByBookId(String bookId){
        String sql = "select * from purchase where BookId='" + bookId + "'";
        System.out.println("In PurchaseDao getPurchasebyBookId 1");

        return template.queryForObject(sql,
                new PurchaseRowMapper());
    }

    public Purchase getPurchaseByBill(String bill){
        String sql = "select * from purchase where BillNumber='" + bill + "'";
        System.out.println("In PurchaseDao getPurchasebyBill 1");

        return template.queryForObject(sql,
                new PurchaseRowMapper());
    }


    public List<Purchase> getPurchaseOfSupplier(String SupplierId){
        String sql = "select * from purchase where SupplierId='" + SupplierId + "'";
        System.out.println("In PurchaseDao getPurchaseOfSupplier 1");

        return template.query(sql,
                new PurchaseRowMapper());
    }


    public List<Purchase> getPurchaseOfYear(int year){
        String sql = "select * from purchase where PurchaseYear=" + year;
        System.out.println("In PurchaseDao getPurchaseOfYear 1");

        return template.query(sql,
                new PurchaseRowMapper());
    }


    public List<Purchase> getAllPurchases(){
        String sql = "select * from purchase";
        System.out.println("In PurchaseDao getAllPurchases 1");

        return template.query(sql,
                new PurchaseRowMapper());
    }

    public void save(Purchase purchase){
        String sql = "insert into purchase (BookId, BillNumber, PurchaseYear, Cost, SupplierId) values ('"
                + purchase.getBookId() + "','" + purchase.getBillNumber() + "'," + purchase.getPurchaseYear()
                + "," + purchase.getCost() + ",'" + purchase.getSupplierId() + "')";

        System.out.println("In PurchaseDao save method 1");
        template.update(sql);
    }

    public void update(Purchase purchase){
        String sql = "update purchase set BillNumber = '" + purchase.getBillNumber() + "', Cost = " + purchase.getCost()
                + ", PurchaseYear = " + purchase.getPurchaseYear() + ", SupplierId = '" + purchase.getSupplierId()
                + "' where BookId = '" + purchase.getBookId() + "'";
        System.out.println("Inside PurchaseDao, update method");
        template.update(sql);
    }

}
