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
    private int quantityLoaned;


    // ==================== Constructors ====================
    /**/
    public Stock() {
    }

    public Stock(StockId stockId, int quantity, int quantityLoaned) {
        this.stockId = stockId;
        this.quantity = quantity;
        this.quantityLoaned = quantityLoaned;
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

    public int getQuantityLoaned() {
        return quantityLoaned;
    }
    public void setQuantityLoaned(int quantityLoaned) {
        this.quantityLoaned = quantityLoaned;
    }


    // ==================== Methods ====================

}
