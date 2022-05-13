package com.school.work.models;

public class Userinfo{
    private String username;
    private String password;
    private int employeeId;
    private int LibraryId;
    private String role;

    public String getUserName(){
        return this.username;
    }

    public void setUserName(String username){
        this.username = username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public int getEmployeeId(){
        return this.employeeId;
    }

    public void setEmployeeId(int employeeId){
        this.employeeId = employeeId;
    }

    public int getLibraryId() {
        return LibraryId;
    }

    public void setLibraryId(int libraryId) {
        LibraryId = libraryId;
    }

    public String getRole(){
        return this.role;
    }

    public void setRole(String role){
        this.role = role;
    }
    
}