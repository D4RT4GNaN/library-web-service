package org.openclassroom.projet.model.database.usager;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Roles")
public class Role {

    @Id
    private int id;

    @NotNull
    @NotEmpty
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private List<Usager> usagers;

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

    public List<Usager> getUsagers() {
        return usagers;
    }
    public void setUsagers(List<Usager> usagers) {
        this.usagers = usagers;
    }
}
