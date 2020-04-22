package org.openclassroom.projet.model.database.service;

import org.openclassroom.projet.model.database.library.Book;
import org.openclassroom.projet.model.database.library.Library;
import org.openclassroom.projet.model.database.usager.Usager;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class LoanId implements Serializable {

    // ==================== Attributes ====================
    @Column(name = "borrowing_date")
    private Date borrowingDate;

    //@OneToOne(fetch = FetchType.LAZY)
    @OneToOne
    @JoinColumn(name = "reference_book")
    private Book book;

    @OneToOne
    @JoinColumn(name = "number_ref_library")
    private Library library;

    @OneToOne
    @JoinColumn(name = "usager_id")
    private Usager usager;



    // ==================== Constructors ====================
    public LoanId() {}

    public LoanId(Book book, Library library, Usager usager) {
        this.borrowingDate = new Date();
        this.book = book;
        this.library = library;
        this.usager = usager;
    }



    // ==================== Getters/Setters ====================
    public Date getBorrowingDate() {
        return borrowingDate;
    }
    public void setBorrowingDate(Date borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }

    public Library getLibrary() {
        return library;
    }
    public void setLibrary(Library library) {
        this.library = library;
    }

    public Usager getUsager() {
        return usager;
    }
    public void setUsager(Usager usager) {
        this.usager = usager;
    }



    // ==================== Methods ====================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanId loanId = (LoanId) o;
        return Objects.equals(borrowingDate, loanId.borrowingDate) &&
                Objects.equals(book, loanId.book) &&
                Objects.equals(library, loanId.library) &&
                Objects.equals(usager, loanId.usager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrowingDate, book, library, usager);
    }
}
