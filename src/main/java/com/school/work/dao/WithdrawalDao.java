package com.school.work.dao;


import com.school.work.models.Withdrawal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;

@Transactional
@Repository
public class WithdrawalDao {
    private JdbcTemplate template;
    int id = 1;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate template){
        this.template = template;
    }

    public List<Withdrawal> getAllWithdrawals(){
        String sql = "select * from withdrawal";

        return template.query(sql, new WithdrawalRowMapper());
    }

    public int save(Withdrawal withdrawal){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String withdrawalDate = sdf.format(withdrawal.getWithdrawalDate());

        id = id+1;

        String sql = "insert into withdrawal (WithdrawalId,WithdrawalDate,Reason,BookId,BillNumber) "
                + "values ('" + id + "','" + withdrawalDate + "','" + withdrawal.getReason()
                + "','" + withdrawal.getBookId() + "','" + withdrawal.getBillNumber() + "')";

        System.out.println("Withdrawal save: " + sql);

        template.update(sql);

        return id;
    }

    public void delete(String id){
        String sql = "delete from withdrawal where WithdrawalId='" + id + "'";

        template.update(sql);
    }
}
