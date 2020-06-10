package org.openclassroom.projet.model.database.service;

import org.openclassroom.projet.model.enums.BorrowingStatusEnum;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "borrowing")
public class Borrowing {

    // ==================== Attributes ====================
    @EmbeddedId
    private BorrowingId borrowingId;

    @NotNull
    @NotEmpty
    @Column(name = "expiry_date")
    private Date expiryDate;

    @NotNull
    @NotEmpty
    private Boolean extended;

    @NotNull
    @NotEmpty
    private String status;

    @NotNull
    @NotEmpty
    private int quantity;



    // ==================== Constructors ====================
    public Borrowing() {
    }

    public Borrowing(BorrowingId borrowingId) {
        this.borrowingId = borrowingId;
        this.expiryDate = calculateExpiryDate();
        this.extended = false;
        this.status = BorrowingStatusEnum.OUTSTANDING.name();
        this.quantity = 1;
    }

    public Borrowing(BorrowingId borrowingId, Date expiryDate, Boolean extended, String status, int quantity) {
        this.borrowingId = borrowingId;
        this.expiryDate = expiryDate;
        this.extended = extended;
        this.status = status;
        this.quantity = quantity;
    }



    // ==================== Getters/Setters ====================
    public BorrowingId getBorrowingId() {
        return borrowingId;
    }
    public void setBorrowingId(BorrowingId borrowingId) {
        this.borrowingId = borrowingId;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Boolean getExtended() {
        return extended;
    }
    public void setExtended(Boolean extended) {
        this.extended = extended;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    // ==================== Methods ====================
    /**
     * Calculates the expiration date of the borrowing from the configuration in the .properties file.
     *
     * @return The expiry date
     * */
    private Date calculateExpiryDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        calendar.add(Calendar.WEEK_OF_MONTH, 2);
        return calendar.getTime();
    }



    /**
     * Recalculate the expiry date.
     * */
    private void extendExpiryDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.expiryDate);
        calendar.add(Calendar.WEEK_OF_MONTH, 2);
        this.setExpiryDate(calendar.getTime());
    }



    /**
     * Extend the borrowing (Change Extended, change the status and recalulate the expiry date).
     * */
    public void extendBorrowing() {
        this.setExtended(true);
        this.setStatus(BorrowingStatusEnum.EXTENDED.name());
        this.extendExpiryDate();
    }



    /**
     * Check if the borrowing is not overdue.
     *
     * return true if teh current date is before the expiry date
     * */
    public boolean isValid() {
        boolean isValid = this.expiryDate.after(new Date());
        if (!isValid && !this.getStatus().equals(BorrowingStatusEnum.RETURNED.name())) { this.setStatus(BorrowingStatusEnum.OVERDUE.name()); }
        return isValid;
    }
}
