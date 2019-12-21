package org.openclassroom.projet.model.database.usager;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
public class VerificationToken {
    private static final int EXPIRATION = 60 * 24;

    // ==================== Attributes ====================
    @Id
    @GeneratedValue
    private int id;

    private String token;

    @OneToOne(targetEntity = Usager.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "usager_id")
    private Usager usager;

    @Column(name = "expiry_date")
    private Date expiryDate;


    // ==================== Constructors ====================
    public VerificationToken() {
    }

    public VerificationToken(Usager usager, String token) {
        this.token = token;
        this.usager = usager;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }


    // ==================== Getters/Setters ====================
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public Usager getUsager() {
        return usager;
    }
    public void setUsager(Usager usager) {
        this.usager = usager;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }


    // ==================== Methods ====================
    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}
