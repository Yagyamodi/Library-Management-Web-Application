package com.school.work.models;

import java.sql.Date;

public class Issues {
    private int IssueId;
    private int LibraryId;
    private String BookId;
    private Date IssueDate;
    private Date DueDate;
    private int Warning = 0;
    private int CurrentDelay = 0;
    private String PhoneNumber;
    private Date ReturnDate;
    private int Fine = 0;
    private String Remarks = "All good";

    public int getIssueId() {
        return IssueId;
    }

    public void setIssueId(int issueId) {
        IssueId = issueId;
    }

    public int getLibraryId() {
        return LibraryId;
    }

    public void setLibraryId(int libraryid) {
        LibraryId = libraryid;
    }

    public String getBookId() {
        return BookId;
    }

    public void setBookId(String bookId) {
        BookId = bookId;
    }

    public Date getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(Date issueDate) {
        IssueDate = issueDate;
    }

    public Date getDueDate() {
        return DueDate;
    }

    public void setDueDate(Date dueDate) {
        DueDate = dueDate;
    }

    public int getWarning() {
        return Warning;
    }

    public void setWarning(int warning) {
        Warning = warning;
    }

    public int getCurrentDelay() {
        return CurrentDelay;
    }

    public void setCurrentDelay(int currentDelay) {
        CurrentDelay = currentDelay;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Date getReturnDate() {
        return ReturnDate;
    }

    public void setReturnDate(Date returnDate) {
        ReturnDate = returnDate;
    }

    public int getFine() {
        return Fine;
    }

    public void setFine(int fine) {
        Fine = fine;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }
}
