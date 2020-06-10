package org.openclassroom.projet.model.database.usager;

import org.openclassroom.projet.model.security.annotations.PasswordMatches;
import org.openclassroom.projet.model.security.annotations.ValidEmail;
import org.openclassroom.projet.model.security.annotations.ValidPassword;

import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Transition class for releasing a user object.
 * */
@PasswordMatches
public class UsagerDto {

    // ==================== Attributes ====================
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

    private boolean enabled;


    // ==================== Constructors ====================
    public UsagerDto() {
        super();
        this.enabled = false;
    }

    public UsagerDto(Usager usager) {
        super();
        this.enabled = false;
        this.email = usager.getEmail();
        this.password = usager.getPassword();
        this.firstName = usager.getFirstName();
        this.lastName = usager.getLastName();
        this.address = usager.getAddress();
        this.enabled = usager.isEnabled();
    }


    // ==================== Getters/Setters ====================
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

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public boolean isEnabled() {
        return this.enabled;
    }

}
