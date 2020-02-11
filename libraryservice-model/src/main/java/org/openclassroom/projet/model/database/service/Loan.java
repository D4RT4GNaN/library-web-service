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



    // ==================== Constructors ====================
    /**/
    public Loan() {
    }

    public Loan(LoanId loanId) {
        this.loanId = loanId;
        this.expiryDate = calculateExpiryDate();
        this.extended = false;
        this.status = LoanStatusEnum.OUTSTANDING.name();
    }

    public Loan(LoanId loanId, Date expiryDate, Boolean extended, String status) {
        this.loanId = loanId;
        this.expiryDate = expiryDate;
        this.extended = extended;
        this.status = status;
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


    // ==================== Methods ====================
    private Date calculateExpiryDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        calendar.add(Calendar.WEEK_OF_MONTH, 2);
        return calendar.getTime();
    }


}
