package org.openclassroom.projet.model.usager;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Usager {
    // ==================== Attributes ====================
    @Id
    @GeneratedValue
    private int id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String mail;
    private String adress;
    private String role;


    // ==================== Constructors ====================
    /**/
    public Usager() {

    }


    // ==================== Getters/Setters ====================
    public int getId() { return id; }
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAdress() {
        return adress;
    }
    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }


    // ==================== Methods ====================
}
