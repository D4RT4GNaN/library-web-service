package org.openclassroom.projet.model.database.library;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StockId implements Serializable {

    // ==================== Attributes ====================
    @Column(name = "number_ref_library")
    private int numberRefLibrary;

    @Column(name = "reference_book")
    private String referenceBook;


    // ==================== Constructors ====================
    public StockId() {}

    public StockId(int numberRefLibrary, String referenceBook) {
        this.numberRefLibrary = numberRefLibrary;
        this.referenceBook = referenceBook;
    }


    // ==================== Getters/Setters ====================
    public int getNumberRefLibrary() {
        return numberRefLibrary;
    }
    public void setNumberRefLibrary(int numberRefLibrary) {
        this.numberRefLibrary = numberRefLibrary;
    }

    public String getReferenceBook() {
        return referenceBook;
    }
    public void setReferenceBook(String referenceBook) {
        this.referenceBook = referenceBook;
    }


    // ==================== Methods ====================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockId stockId = (StockId) o;
        return Objects.equals(numberRefLibrary, stockId.numberRefLibrary) &&
                Objects.equals(referenceBook, stockId.referenceBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberRefLibrary, referenceBook);
    }

}
