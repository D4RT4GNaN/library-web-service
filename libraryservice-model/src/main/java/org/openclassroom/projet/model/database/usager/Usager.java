package org.openclassroom.projet.model.database.usager;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "usager")
public class Usager implements Serializable, UserDetails {

    // ==================== Attributes ====================
    @Id
    @GeneratedValue
    private int id;

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String address;

    @Column(name = "enabled")
    private boolean enabled;


    // ==================== Constructors ====================
    /**/
    public Usager() {
        super();
        this.enabled = false;

    }

    public Usager(UsagerDto usagerDto) {
        this.email = usagerDto.getEmail();
        this.password = encodePassword(usagerDto.getPassword());
        this.firstName = usagerDto.getFirstName();
        this.lastName = usagerDto.getLastName();
        this.address = usagerDto.getAddress();
        this.enabled = false;
    }

    public Usager(String email, String password, String firstName, String lastName, String address) {
        super();
        this.enabled = false;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }


    // ==================== Getters/Setters ====================
    public int getId() { return id; }
    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    // ==================== Methods ====================
    @Override
    public String getUsername() { return email; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

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
        return this.enabled;
    }

    private String encodePassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

}
