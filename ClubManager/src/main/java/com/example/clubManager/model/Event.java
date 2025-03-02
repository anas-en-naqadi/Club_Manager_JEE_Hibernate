package com.example.clubManager.model;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "EVENEMENT")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EVENEMENT")
    private int idEvenement;

    @Column(name = "NOM", nullable = false)
    private String nom;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "DATE_EVENEMENT", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEvenement;

    @ManyToOne
    @JoinColumn(name = "ID_CLUB", nullable = false)
    private Club club;  // Association avec la classe Club

    @ManyToMany
    @JoinTable(
        name = "MEMBRE_EVENEMENT", 
        joinColumns = @JoinColumn(name = "ID_EVENEMENT"), 
        inverseJoinColumns = @JoinColumn(name = "ID_MEMBRE")
    )
    private Set<Member> members;  

    public Event() {}
    // Getters and Setters
    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
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

    public Date getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }
}
