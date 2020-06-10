package org.openclassroom.projet.model.database.library;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StockId implements Serializable {

    // ==================== Attributes ====================
    @Column(name = "library_number_ref")
    private int libaryNumberRef;

    @Column(name = "book_reference")
    private String bookReference;


    // ==================== Constructors ====================
    public StockId() {}

    public StockId(int libaryNumberRef, String bookReference) {
        this.libaryNumberRef = libaryNumberRef;
        this.bookReference = bookReference;
    }


    // ==================== Getters/Setters ====================
    public int getLibaryNumberRef() {
        return libaryNumberRef;
    }
    public void setLibaryNumberRef(int libaryNumberRef) {
        this.libaryNumberRef = libaryNumberRef;
    }

    public String getBookReference() {
        return bookReference;
    }
    public void setBookReference(String bookReference) {
        this.bookReference = bookReference;
    }


    // ==================== Methods ====================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockId stockId = (StockId) o;
        return Objects.equals(libaryNumberRef, stockId.libaryNumberRef) &&
                Objects.equals(bookReference, stockId.bookReference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libaryNumberRef, bookReference);
    }

}
