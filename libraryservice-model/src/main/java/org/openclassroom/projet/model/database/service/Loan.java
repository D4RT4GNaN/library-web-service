package org.openclassroom.projet.model.database.service;

import org.openclassroom.projet.model.enums.LoanStatusEnum;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "loan")
public class Loan {

    // ==================== Attributes ====================
    @EmbeddedId
    private LoanId loanId;

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
    /**/
    public Loan() {
    }

    public Loan(LoanId loanId) {
        this.loanId = loanId;
        this.expiryDate = calculateExpiryDate();
        this.extended = false;
        this.status = LoanStatusEnum.OUTSTANDING.name();
        this.quantity = 1;
    }

    public Loan(LoanId loanId, Date expiryDate, Boolean extended, String status, int quantity) {
        this.loanId = loanId;
        this.expiryDate = expiryDate;
        this.extended = extended;
        this.status = status;
        this.quantity = quantity;
    }



    // ==================== Getters/Setters ====================
    public LoanId getLoanId() {
        return loanId;
    }
    public void setLoanId(LoanId loanId) {
        this.loanId = loanId;
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
    private Date calculateExpiryDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        calendar.add(Calendar.WEEK_OF_MONTH, 2);
        return calendar.getTime();
    }

    private void extendExpiryDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.expiryDate);
        calendar.add(Calendar.WEEK_OF_MONTH, 2);
        this.setExpiryDate(calendar.getTime());
    }

    public void extendLoan() {
        this.setExtended(true);
        this.setStatus(LoanStatusEnum.EXTENDED.name());
        this.extendExpiryDate();
    }

    public boolean isValid() {
        boolean isValid = this.expiryDate.after(new Date());
        if (!isValid) { this.setStatus(LoanStatusEnum.OVERDUE.name()); }
        return isValid;
    }
}
