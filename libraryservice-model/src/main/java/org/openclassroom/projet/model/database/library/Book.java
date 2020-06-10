package org.openclassroom.projet.model.database.library;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "book")
public class Book {

    // ==================== Attributes ====================
    @Id
    private String reference;

    @NotNull
    @NotEmpty
    @Column(name="image_url")
    private String imageUrl;

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String author;

    @NotNull
    private String synopsis;

    @NotNull
    @NotEmpty
    private String category;

    @NotNull
    @NotEmpty
    private String publisher;

    @NotNull
    @NotEmpty
    private String language;

    private int mark;


    // ==================== Constructors ====================
    public Book() {
    }

    public Book(String reference, String imageUrl, String title, String author, String synopsis, String category, String publisher, String language, int mark) {
        this.reference = reference;
        this.imageUrl = imageUrl;
        this.title = title;
        this.author = author;
        this.synopsis = synopsis;
        this.category = category;
        this.publisher = publisher;
        this.language = language;
        this.mark = mark;
    }


    // ==================== Getters/Setters ====================
    public String getReference() {
        return reference;
    }
    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSynopsis() {
        return synopsis;
    }
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }

    public int getMark() {
        return mark;
    }
    public void setMark(int mark) {
        this.mark = mark;
    }

}
