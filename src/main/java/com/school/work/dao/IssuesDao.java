package com.school.work.dao;

import com.school.work.models.Issues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;


@Transactional
@Repository
public class IssuesDao {
    private JdbcTemplate template;

    @Autowired
    public void setTemplate(JdbcTemplate template){
        this.template = template;
    }

    public Issues getIssueByIssueId(int IssueId){
        String sql = "select * from issues where IssueId=" + IssueId;
        return template.queryForObject(sql,
                new IssuesRowMapper());
    }

    public List<Issues> getAllIssues() {
        String sql="select * from issues";
        return template.query(sql,
                new IssuesRowMapper());
    }

    public List<Issues> getMemberIssueDetails(int LibraryId){
        String sql = "select * from issues where LibraryId=" + LibraryId;
        return template.query(sql,
                new IssuesRowMapper());
    }

    public List<Issues> getBookIssueDetails(String BookId){
        String sql = "select * from issues where BookId=" + BookId;
        return template.query(sql,
                new IssuesRowMapper());
    }

    public int save(Issues issue) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String isuDate = sdf.format(issue.getIssueDate());
        String dueDate = sdf.format(issue.getDueDate());

        String sql = "insert into issues (LibraryId,BookId,IssueDate,DueDate,PhoneNumber,Remarks)" +
                "values (" + issue.getLibraryId() + ",'" + issue.getBookId() + "','" + isuDate + "','" +
                dueDate + "','" + issue.getPhoneNumber() + "','" + issue.getRemarks() + "')";

        System.out.println("Issue save: " + sql);

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

    public void update(Issues issue) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy=MM-dd");
        String returnDate = sdf.format(issue.getReturnDate());
        int issueId = issue.getIssueId();

        System.out.println("In IssuesDao update1 " + issueId);
        String sql = "update issues set Warning=" + issue.getWarning() + ", CurrentDelay=" + issue.getCurrentDelay() + ", Fine=" +
                issue.getFine() + ", ReturnDate='" + returnDate  + "' , Remarks='" + issue.getRemarks() + "' where IssueId=" + issueId;

        System.out.println("sql: " + sql);
        System.out.println("In IssuesDao update2");

        template.update(sql);
    }

    public void updateCurrentDelay(Issues issue) {
        Date todayDate = Date.valueOf(LocalDate.now());
        Date dueDate = issue.getDueDate();

        System.out.println("In IssuesDao current delay1 " + todayDate);

        int diffInDays = (int)( (todayDate.getTime() - dueDate.getTime() ) / (1000 * 60 * 60 * 24) );
        System.out.println("In IssuesDao current delay2");

        String sql = "update issues set CurrentDelay=" + diffInDays + "where IssueId=" + issue.getIssueId();
        template.update(sql);
    }

    public Issues getIssueId(int memberId, String bookId) {
        String sql = "select * from issues where LibraryId=" + memberId + " AND BookId='" + bookId + "'";

        return template.queryForObject(sql, new IssuesRowMapper());
    }

    public int getActiveIssueCount(List<Issues> isuList) {
        int count =0;
        for(int i=0;i<isuList.size();++i){
            if(isuList.get(i).getReturnDate()==null)    count++;
        }
        return count;
    }
}
