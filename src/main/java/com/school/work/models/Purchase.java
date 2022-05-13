package com.school.work.models;

public class Purchase {
    private String BookId;
    private String BillNumber;
    private int PurchaseYear;
    private int Cost;
    private String SupplierId;

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

    public int getPurchaseYear() {
        return PurchaseYear;
    }

    public void setPurchaseYear(int purchaseYear) {
        PurchaseYear = purchaseYear;
    }

    public int getCost() {
        return Cost;
    }

    public void setCost(int cost) {
        Cost = cost;
    }

    public String getSupplierId() {
        return SupplierId;
    }

    public void setSupplierId(String supplierId) {
        SupplierId = supplierId;
    }
}
