package org.openclassroom.projet.model.database.library;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "stock")
public class Stock {

    // ==================== Attributes ====================
    @EmbeddedId
    private StockId stockId;

    @NotNull
    @NotEmpty
    private int quantity;

    @NotNull
    @NotEmpty
    @Column(name = "quantity_borrowed")
    private int quantityBorrowed;


    // ==================== Constructors ====================
    public Stock() {
    }

    public Stock(StockId stockId, int quantity, int quantityBorrowed) {
        this.stockId = stockId;
        this.quantity = quantity;
        this.quantityBorrowed = quantityBorrowed;
    }


    // ==================== Getters/Setters ====================
    public StockId getStockId() {
        return stockId;
    }
    public void setStockId(StockId stockId) {
        this.stockId = stockId;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantityBorrowed() {
        return quantityBorrowed;
    }
    public void setQuantityBorrowed(int quantityBorrowed) {
        this.quantityBorrowed = quantityBorrowed;
    }

}
