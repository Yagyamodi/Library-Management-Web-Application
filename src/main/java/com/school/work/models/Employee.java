package com.school.work.models;
import java.sql.Date;

public class Employee{
    private int employeeId;
    private String Fname;
    private String Lname;
    private String gender;
    private Date DOB;
    private String address;
    private String contact;
    private String email;
    private String designation;

    public int getEmployeeId(){
        return this.employeeId;
    }

    public void setEmployeeId(int employeeId){
        this.employeeId = employeeId;
    }

    public String getFname(){
        return this.Fname;
    }

    public void setFname(String Fname){
        this.Fname = Fname;
    }

    public String getLname(){
        return this.Lname;
    }

    public void setLname(String Lname){
        this.Lname = Lname;
    }

    public String getGender(){
        return this.gender;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public Date getDOB(){
        return this.DOB;
    }

    public void setDOB(Date DOB){
        this.DOB = DOB;
    }

    public String getAddress(){
        return this.address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getContact(){
        return this.contact;
    }

    public void setContact(String contact){
        this.contact = contact;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getDesignation(){
        return this.designation;
    }

    public void setDesignation(String designation){
        this.designation = designation;
    }
    
}