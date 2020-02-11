package org.openclassroom.projet.model.database.service;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class LoanId implements Serializable {

    // ==================== Attributes ====================
    @Column(name = "borrowing_date")
    private Date borrowingDate;

    @Column(name = "reference_book")
    private String referenceBook;

    @Column(name = "usager_id")
    private int usagerId;


    // ==================== Constructors ====================
    public LoanId() {}

    public LoanId(Date borrowingDate, String referenceBook, int usagerId) {
        this.borrowingDate = borrowingDate;
        this.referenceBook = referenceBook;
        this.usagerId = usagerId;
    }


    // ==================== Getters/Setters ====================
    public Date getBorrowingDate() {
        return borrowingDate;
    }
    public void setBorrowingDate(Date borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public String getReferenceBook() {
        return referenceBook;
    }
    public void setReferenceBook(String referenceBook) {
        this.referenceBook = referenceBook;
    }

    public int getUsagerId() {
        return usagerId;
    }
    public void setUsagerId(int usagerId) {
        this.usagerId = usagerId;
    }


    // ==================== Methods ====================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanId loanId = (LoanId) o;
        return usagerId == loanId.usagerId &&
                Objects.equals(borrowingDate, loanId.borrowingDate) &&
                Objects.equals(referenceBook, loanId.referenceBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrowingDate, referenceBook, usagerId);
    }
}
