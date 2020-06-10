package org.openclassroom.projet.model.database.library;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "library")
public class Library {

    // ==================== Attributes ====================
    @Id
    @Column(name = "number_ref")
    private int numberRef;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String address;



    // ==================== Constructors ====================
    public Library() {
    }

    public Library(int numberRef, String name, String address) {
        this.numberRef = numberRef;
        this.name = name;
        this.address = address;
    }


    // ==================== Getters/Setters ====================

    public int getNumberRef() {
        return numberRef;
    }
    public void setNumberRef(int numberRef) {
        this.numberRef = numberRef;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

}
