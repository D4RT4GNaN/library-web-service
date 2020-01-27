package org.openclassroom.projet.model.database.usager;

import org.openclassroom.projet.model.enums.RoleEnum;
import org.openclassroom.projet.model.security.annotations.EnumMatches;
import org.openclassroom.projet.model.security.annotations.PasswordMatches;
import org.openclassroom.projet.model.security.annotations.ValidEmail;
import org.openclassroom.projet.model.security.annotations.ValidPassword;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
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
    @ValidEmail
    private String email;

    @NotNull
    @NotEmpty
    @ValidPassword
    private String password;

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
    private String address;

    @EnumMatches(enumClass = RoleEnum.class)
    private String role;

    @Column(name = "enabled")
    private boolean enabled;


    // ==================== Constructors ====================
    /**/
    public Usager() {
        super();
        this.enabled = false;
        this.role = RoleEnum.USER.name();
    }

    public Usager(String email, String password, String confirmPassword, String firstName, String lastName, String address, RoleEnum role) {
        super();
        this.enabled = false;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.role = role.name();
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

    public String getRoles() { return role; }
    public void setRoles(RoleEnum role) { this.role = role.name(); }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    // ==================== Methods ====================
    @Override
    public String getUsername() { return email; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(getRoles());
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

}
