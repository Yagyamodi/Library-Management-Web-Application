package com.school.work.models;

import java.sql.Date;

public class Member {
    private int LibraryId;
    private String Name;
    private String Gender;
    private String Category;
    private String PhoneNumber;
    private String AdhaarNumber;
    private Date DOB;
    private String Education;
    private String Profession;
    private String FatherName;
    private Date JoinDate;
    private Date EndDate;
    private float AnnualFees = 105;
    private int Limit = 2;
    private int Age;
    private String CurrentLocation;
    private String CurrentCity = "Jhunjhunu";
    private  String CurrentState = "Rajasthan";
    private int CurrentPincode = 333001;
    private String GuarantorName;
    private String GuarantorDepartment;
    private String GuarantorEmail;
    private String GuarantorPhone;
    private String Relation;

    public int getLibraryId() {
        return LibraryId;
    }

    public void setLibraryId(int libraryId) {
        LibraryId = libraryId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAdhaarNumber() {
        return AdhaarNumber;
    }

    public void setAdhaarNumber(String adhaarNumber) {
        AdhaarNumber = adhaarNumber;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getEducation() {
        return Education;
    }

    public void setEducation(String education) {
        Education = education;
    }

    public String getProfession() {
        return Profession;
    }

    public void setProfession(String profession) {
        Profession = profession;
    }

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public Date getJoinDate() {
        return JoinDate;
    }

    public void setJoinDate(Date joinDate) {
        JoinDate = joinDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public float getAnnualFees() {
        return AnnualFees;
    }

    public void setAnnualFees(float annualFees) {
        AnnualFees = annualFees;
    }

    public int getLimit() {
        return Limit;
    }

    public void setLimit(int limit) {
        //if(limit<2) limit=2;
        Limit = limit;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getCurrentLocation() {
        return CurrentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        CurrentLocation = currentLocation;
    }

    public String getCurrentCity() {
        return CurrentCity;
    }

    public void setCurrentCity(String currentCity) {
        CurrentCity = currentCity;
    }

    public String getCurrentState() {
        return CurrentState;
    }

    public void setCurrentState(String currentState) {
        CurrentState = currentState;
    }

    public int getCurrentPincode() {
        return CurrentPincode;
    }

    public void setCurrentPincode(int currentPincode) {
        CurrentPincode = currentPincode;
    }

    public String getGuarantorName() {
        return GuarantorName;
    }

    public void setGuarantorName(String guarantorName) {
        GuarantorName = guarantorName;
    }

    public String getGuarantorDepartment() {
        return GuarantorDepartment;
    }

    public void setGuarantorDepartment(String guarantorDepartment) {
        GuarantorDepartment = guarantorDepartment;
    }

    public String getGuarantorEmail() {
        return GuarantorEmail;
    }

    public void setGuarantorEmail(String guarantorEmail) {
        GuarantorEmail = guarantorEmail;
    }

    public String getGuarantorPhone() {
        return GuarantorPhone;
    }

    public void setGuarantorPhone(String guarantorPhone) {
        GuarantorPhone = guarantorPhone;
    }

    public String getRelation() {
        return Relation;
    }

    public void setRelation(String relation) {
        Relation = relation;
    }
}
