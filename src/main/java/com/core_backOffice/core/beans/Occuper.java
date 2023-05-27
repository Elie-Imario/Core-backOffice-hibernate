package com.core_backOffice.core.beans;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Occuper")
public class Occuper {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "id_occ_key_generator")
    @TableGenerator(name = "id_occ_key_generator",
            table = "pk_occ",
            pkColumnName = "name",
            valueColumnName = "value",
            allocationSize = 1)
    @Column(name = "occuper_Id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Id_prof")
    private Professeur prof;

    @ManyToOne
    @JoinColumn(name = "Id_Salle")
    private Salle salle;

    private Date date_occupation;

    public Occuper() {}

    public Occuper(Professeur professeur, Salle salle, Date date_occupation) {
        this.prof = professeur;
        this.salle = salle;
        this.date_occupation = date_occupation;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate_occupation() {
        return date_occupation;
    }

    public void setDate_occupation(Date date_occupation) {
        this.date_occupation = date_occupation;
    }

    public Professeur getProf() {
        return prof;
    }

    public void setProf(Professeur professeur) {
        this.prof = professeur;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }
}
