package com.school.work.models;

import java.sql.Date;

public class Withdrawal {
    private String WithdrawalId;
    private Date WithdrawalDate;
    private String Reason;
    private String BookId;
    private String BillNumber;

    public String getWithdrawalId() {
        return WithdrawalId;
    }

    public void setWithdrawalId(String withdrawalId) {
        WithdrawalId = withdrawalId;
    }

    public Date getWithdrawalDate() {
        return WithdrawalDate;
    }

    public void setWithdrawalDate(Date withdrawalDate) {
        WithdrawalDate = withdrawalDate;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getBookId() {
        return BookId;
    }

    public void setBookId(String bookId) {
        BookId = bookId;
    }

    public String getBillNumber() {
        return BillNumber;
    }

    public void setBillNumber(String billNumber) {
        BillNumber = billNumber;
    }
}
