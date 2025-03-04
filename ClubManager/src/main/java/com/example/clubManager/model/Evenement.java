package com.example.clubManager.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "evenement")
public class Evenement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EVENEMENT")
    private int idEvenement;

    @Column(name = "NOM", nullable = false, length = 100)
    private String nom;

    @Column(name = "DESCRIPTION")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_EVENEMENT", nullable = false)
    private Date dateEvenement;

    @ManyToOne
    @JoinColumn(name = "ID_CLUB", nullable = false)
    private Club club;

    @ManyToMany(mappedBy = "evenements")
    private Set<Etudiant> participants = new HashSet<>();

    // Constructors
    public Evenement() {}

    public Evenement(String nom, String description, Date dateEvenement, Club club) {
        this.nom = nom;
        this.description = description;
        this.dateEvenement = dateEvenement;
        this.club = club;
    }

    // Getters and Setters
    public int getIdEvenement() { return idEvenement; }
    public void setIdEvenement(int idEvenement) { this.idEvenement = idEvenement; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Date getDateEvenement() { return dateEvenement; }
    public void setDateEvenement(Date dateEvenement) { this.dateEvenement = dateEvenement; }
    public Club getClub() { return club; }
    public void setClub(Club club) { this.club = club; }
    public Set<Etudiant> getParticipants() { return participants; }
    public void setParticipants(Set<Etudiant> participants) { this.participants = participants; }
}