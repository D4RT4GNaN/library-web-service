package org.openclassroom.projet.model.database.usager;

import javax.persistence.*;
import java.util.Set;

@Entity
//@Table(name = "role")
public class Role {

    // ========== Attributes ==========
    private int id;
    private String name;
    //private Set users;


    // ========== Getters/Setters ===========
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    /*@ManyToMany(mappedBy = "roles")
    public Set getUsers() {
        return users;
    }
    public void setUsers(Set users) {
        this.users = users;
    }*/
}
