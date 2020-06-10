package org.openclassroom.projet.model.database.service;

import org.openclassroom.projet.model.database.library.Book;
import org.openclassroom.projet.model.database.library.Library;
import org.openclassroom.projet.model.database.usager.Usager;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class BorrowingId implements Serializable {

    // ==================== Attributes ====================
    @Column(name = "borrowing_date")
    private Date borrowingDate;

    @OneToOne
    @JoinColumn(name = "book_reference")
    private Book book;

    @OneToOne
    @JoinColumn(name = "library_number_ref")
    private Library library;

    @OneToOne
    @JoinColumn(name = "usager_id")
    private Usager usager;



    // ==================== Constructors ====================
    public BorrowingId() {}

    public BorrowingId(Book book, Library library, Usager usager) {
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
        BorrowingId borrowingId = (BorrowingId) o;
        return Objects.equals(borrowingDate, borrowingId.borrowingDate) &&
                Objects.equals(book, borrowingId.book) &&
                Objects.equals(library, borrowingId.library) &&
                Objects.equals(usager, borrowingId.usager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrowingDate, book, library, usager);
    }
}
