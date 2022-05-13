package com.school.work.dao;

import com.school.work.models.Book;
import com.school.work.models.Member;
import com.school.work.models.Member__emails;
import lombok.ToString;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public class MemberDao {
    private JdbcTemplate template;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String LastDate = "2020-04-29";
    int year=2020;

    @Autowired
    public void setTemplate(JdbcTemplate template){
        this.template = template;
    }

    public Member getMemberByMemberId(int LibraryId){
        String sql = "select * from member where LibraryId=" + LibraryId;
        return template.queryForObject(sql,
                new MemberRowMapper());
    }

    public int save(Member member){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dob = sdf.format(member.getDOB());
        String joinDate = sdf.format(member.getJoinDate());

        String sql = "insert into member (Name,Gender,Category,PhoneNumber,AdhaarNumber,DOB,Education,Profession,FatherName,JoinDate,Age,CurrentLocation,CurrentCity,CurrentPincode,CurrentState,GuarantorName,GuarantorDepartment,GuarantorEmail,GuarantorPhone,Relation) " +
                "values ('" + member.getName() + "','" + member.getGender() + "','" + member.getCategory() + "','" + member.getPhoneNumber() + "','" + member.getAdhaarNumber() + "','" + dob + "','" + member.getEducation() + "','" + member.getProfession() + "','" +
                member.getFatherName() + "','" + joinDate + "'," + member.getAge() + ",'" + member.getCurrentLocation() + "','" + member.getCurrentCity() + "'," +
                member.getCurrentPincode() + ",'" + member.getCurrentState() + "','" + member.getGuarantorName() + "','" + member.getGuarantorDepartment() + "','" + member.getGuarantorEmail() + "','" +
                member.getGuarantorPhone() + "','" + member.getRelation() + "')";

        System.out.println("Member save" + sql);

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

    public void update(Member member){

        String sql = "update member set LibraryId=" + member.getLibraryId() + " , Name='" + member.getName() + "', Gender='" + member.getGender() +
                "', Category='" + member.getCategory() + "', PhoneNumber='" + member.getPhoneNumber() + "', AdhaarNumber='" + member.getAdhaarNumber() + "', Education='" +
                member.getEducation() + "', Profession='" + member.getProfession() + "', FatherName='" + member.getFatherName() +
                "', Age=" + member.getAge() + ", CurrentLocation='" + member.getCurrentLocation() + "', CurrentCity='" + member.getCurrentCity() + "', CurrentPincode=" +
                member.getCurrentPincode() + ", CurrentState='" + member.getCurrentState() + "', GuarantorName='" + member.getGuarantorName() + "', GuarantorDepartment='" + member.getGuarantorDepartment() + "', GuarantorEmail='" +
                member.getGuarantorEmail() +  "', GuarantorPhone='" + member.getGuarantorPhone() +  "', Relation='" + member.getRelation() + "' where LibraryId = " + member.getLibraryId();

        System.out.println("Member update: " + sql);
        template.update(sql);
    }

    public List<Member> getAllMembers() {
        String sql="select * from member";
        return template.query(sql,
                new MemberRowMapper());
    }

    public List<Member> getAllPresentMembers() {
        Date date= Calendar.getInstance().getTime();
        String date2 = sdf.format(date);
        System.out.println("LastDate: " + LastDate + ", dateToday: " + date2);
        String sql="select * from member where "  + date2 + "<= EndDate";
        return template.query(sql,
                new MemberRowMapper());
    }

    public boolean isMemberIdPresent(String libraryId) {
        String sql = "select * from member where LibraryId=" + libraryId;
        Member member = template.queryForObject(sql, new MemberRowMapper());

        if(member==null)    return false;

        return true;
    }

    public void updateDate(){
        int yr= Calendar.getInstance().get(year);
        while(year<yr)  year++;
        String date = Integer.toString(year) + "-04-29";
        LastDate = date;
        return;
    }

    // Member__emails Dao

    public List<Member__emails> getAllMemberEmails(){
        String sql = "select * from member__emails";

        return template.query(sql,
                new MemberEmailsRowMapper());
    }

    public List<String> getMemberEmailsByMemberId(int LibraryId){
        String sql = "select * from member__emails where LibraryId=" + LibraryId;

        List<Member__emails> rs = template.query(sql,
                new MemberEmailsRowMapper());
        List<String> emails = new ArrayList<>();

        for(int i=0;i<rs.size();i=i+1){
            emails.add(rs.get(i).getEmailAddress());
        }
        return emails;
    }

    public void saveEmail(Member__emails res) {
        String sql = "insert into member__emails (LibraryId, EmailAddress) values (" + res.getLibraryId() + ",'" + res.getEmailAddress() + "')";

        template.update(sql);
    }
}
