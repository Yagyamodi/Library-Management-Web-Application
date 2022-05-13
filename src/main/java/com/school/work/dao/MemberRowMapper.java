package com.school.work.dao;

import com.school.work.models.Member;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberRowMapper implements RowMapper<Member>{

    @Override
    public Member mapRow(ResultSet rs, int row) throws SQLException {
        Member e = new Member();
        e.setLibraryId(rs.getInt("LibraryId"));
        e.setName(rs.getString("Name"));
        e.setGender(rs.getString("Gender"));
        e.setCategory(rs.getString("Category"));
        e.setPhoneNumber(rs.getString("PhoneNumber"));
        e.setAdhaarNumber(rs.getString("AdhaarNumber"));
        e.setDOB(rs.getDate("DOB"));
        e.setEducation(rs.getString("Education"));
        e.setProfession(rs.getString("Profession"));
        e.setFatherName(rs.getString("FatherName"));
        e.setJoinDate(rs.getDate("JoinDate"));
        e.setEndDate(rs.getDate("EndDate"));
        e.setAnnualFees(rs.getFloat("AnnualFees"));
        e.setLimit(rs.getInt("Limit"));
        e.setAge(rs.getInt("Age"));
        e.setCurrentLocation(rs.getString("CurrentLocation"));
        e.setCurrentCity(rs.getString("CurrentCity"));
        e.setCurrentState(rs.getString("CurrentState"));
        e.setCurrentPincode(rs.getInt("CurrentPincode"));
        e.setGuarantorName(rs.getString("GuarantorName"));
        e.setGuarantorDepartment(rs.getString("GuarantorDepartment"));
        e.setGuarantorEmail(rs.getString("GuarantorEmail"));
        e.setGuarantorPhone(rs.getString("GuarantorPhone"));
        e.setRelation(rs.getString("Relation"));

        return e;
    }
}
