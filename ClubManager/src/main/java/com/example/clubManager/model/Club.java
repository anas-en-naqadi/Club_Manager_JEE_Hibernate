package com.example.clubManager.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CLUB")
public class Club {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLUB")
    private int id;

    @Column(name = "NOM", nullable = false, length = 30)
    private String nom;

    @Column(name = "DESCRIPTION", nullable = false, length = 100)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CREATION", nullable = false, updatable = false)
    private Date dateCreation = new Date();

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Event> evenements = new HashSet<>();

    @ManyToMany(mappedBy = "clubs")
    private Set<Member> membres = new HashSet<>();

   
    public Club() {
    
    }

    
    public Club(String nom, String description) {
        this.nom = nom;
        this.description = description;
       
    }

    @PrePersist
    protected void onCreate() {
        this.dateCreation = new Date();
    }
  
    public int getIdClub() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Set<Event> getEvenements() {
        return evenements;
    }

    public void setEvenements(Set<Event> evenements) {
        this.evenements = evenements;
    }

    public Set<Member> getMembres() {
        return membres;
    }

    public void setMembres(Set<Member> membres) {
        this.membres = membres;
    }

    // ✅ toString() pour debug facile
    @Override
    public String toString() {
        return "Club{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", dateCreation=" + dateCreation +
                '}';
    }
}
