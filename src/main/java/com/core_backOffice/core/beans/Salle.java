package com.core_backOffice.core.beans;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Salle")
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "id_salle_key_generator")
    @TableGenerator(name = "id_salle_key_generator",
                    table = "pk_salle",
                    pkColumnName = "name",
                    valueColumnName = "value",
                    allocationSize = 1)
    @Column(name = "Id_Salle")
    private Long id_Salle;
    private String designation;

    @OneToMany(mappedBy = "salle", cascade = CascadeType.REMOVE)
    private Set<Occuper> salle_occuper = new HashSet<>();

    public Salle() {}

    public Salle(String designation) {
        this.designation = designation;
    }

    public Set<Occuper> getSalle_occuper() {
        return salle_occuper;
    }

    public void setSalle_occuper(Set<Occuper> salle_occuper) {
        this.salle_occuper = salle_occuper;
    }

    public void setId_Salle(Long id) {
        this.id_Salle = id;
    }

    public Long getId_Salle() {
        return id_Salle;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

}
