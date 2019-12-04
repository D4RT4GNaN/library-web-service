package org.openclassroom.projet.model.database.usager;

import org.openclassroom.projet.model.security.annotations.PasswordMatches;
import org.openclassroom.projet.model.security.annotations.ValidEmail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "usager")
@PasswordMatches
public class Usager implements Serializable, UserDetails {
    // ==================== Attributes ====================
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    @Transient
    private String confirmPassword;

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    @ValidEmail
    private String mail;

    @NotNull
    @NotEmpty
    private String adress;

    @NotNull
    @NotEmpty
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

    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    //@ManyToMany
    //@JoinTable(name = "role", joinColumns = @JoinColumn(name = "usager_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }


    // ==================== Methods ====================
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

}
