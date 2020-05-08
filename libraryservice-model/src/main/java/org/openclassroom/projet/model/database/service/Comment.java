package org.openclassroom.projet.model.database.service;

import org.openclassroom.projet.model.database.usager.Usager;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment {

    // ==================== Attributes ====================
    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "usager_id")
    private Usager usager;

    @NotNull
    @NotEmpty
    @Column(name = "book_reference")
    private String bookReference;

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String content;

    @NotNull
    @NotEmpty
    private Date date;



    // ==================== Constructors ====================
    public Comment() {
    }

    public Comment(int id, Usager usager, String bookReference, String title, String content, Date date) {
        this.id = id;
        this.usager = usager;
        this.bookReference = bookReference;
        this.title = title;
        this.content = content;
        this.date = date;
    }



    // ==================== Getters/Setters ====================
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Usager getUsager() {
        return usager;
    }
    public void setUsager(Usager usager) {
        this.usager = usager;
    }

    public String getBookReference() {
        return bookReference;
    }
    public void setBookReference(String bookReference) {
        this.bookReference = bookReference;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }



    // ==================== Methods ====================

}
