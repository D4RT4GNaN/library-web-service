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

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    private String address;

    @Column(name = "enabled")
    private boolean enabled;


    // ==================== Constructors ====================
    public Usager() {
        super();
        this.enabled = false;

    }

    public Usager(UsagerDto usagerDto) {
        this.email = usagerDto.getEmail();
        this.password = usagerDto.getPassword() != null ? encodePassword(usagerDto.getPassword()) : null;
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
    /**
     * Override method from UserDetails.
     * Return the username of the uer.
     *
     * @return Here, the username is the email.
     * */
    @Override
    public String getUsername() { return email; }



    /**
     * Override method from UserDetails.
     * Not used here.
     * */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }



    /**
     * Override method from UserDetails.
     * Not used here.
     * */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }



    /**
     * Override method from UserDetails.
     * Not used here.
     * */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }



    /**
     * Override method from UserDetails.
     * Not used here.
     * */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }



    /**
     * Indicates whether the account is activated/verified.
     * */
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }



    /**
     * Encode the password with {@link BCryptPasswordEncoder}.
     *
     * @param password - The password to be encoded.
     *
     * @return The encoded password.
     * */
    private String encodePassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

}
