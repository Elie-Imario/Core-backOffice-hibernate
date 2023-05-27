package com.core_backOffice.core.beans;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Professeur")
public class Professeur {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "id_prof_key_generator")
    @TableGenerator(name = "id_prof_key_generator",
            table = "pk_prof",
            pkColumnName = "name",
            valueColumnName = "value",
            allocationSize = 1)
    @Column(name = "Id_prof")
    private Long id_prof;
    private String nom;
    private String prenom;
    private String grade;

    @OneToMany(mappedBy = "prof",
            cascade = CascadeType.REMOVE)
    private Set<Occuper> salle_occuper = new HashSet<>();

    public Professeur() { }

    public Professeur(String nom, String prenom, String grade) {
        this.nom = nom;
        this.prenom = prenom;
        this.grade = grade;
    }

    public Set<Occuper> getSalle_occuper() {
        return salle_occuper;
    }

    public void setSalle_occuper(Set<Occuper> salle_reserver) {
        this.salle_occuper = salle_reserver;
    }

    public void setId_prof(Long id) {
        this.id_prof = id;
    }

    public Long getId_prof() {
        return id_prof;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


}
