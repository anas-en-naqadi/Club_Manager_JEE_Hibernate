package com.example.clubManager.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "membre_club")
@IdClass(MembreClubId.class)
public class MembreClub {
    
    @Id
    @Column(name = "ID_MEMBRE")
    private int idMembre;

    @Id
    @Column(name = "ID_CLUB")
    private int idClub;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_INSCRIPTION")
    private Date dateInscription = new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MEMBRE", insertable = false, updatable = false)
    private Etudiant etudiant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CLUB", insertable = false, updatable = false)
    private Club club;

    public MembreClub() {}

    public MembreClub(Etudiant etudiant, Club club) {
        this.etudiant = etudiant;
        this.club = club;
        this.idMembre = etudiant.getIdEtudiant();
        this.idClub = club.getIdClub();
    }
    @PrePersist
    protected void onCreate() {
        this.dateInscription = new Date();
    }

    // Getters and Setters
    public int getIdMembre() { return idMembre; }
    public void setIdMembre(int idMembre) { this.idMembre = idMembre; }
    public int getIdClub() { return idClub; }
    public void setIdClub(int idClub) { this.idClub = idClub; }
    public Date getDateInscription() { return dateInscription; }
    public void setDateInscription(Date dateInscription) { this.dateInscription = dateInscription; }
    public Etudiant getEtudiant() { return etudiant; }
    public void setEtudiant(Etudiant etudiant) { this.etudiant = etudiant; }
    public Club getClub() { return club; }
    public void setClub(Club club) { this.club = club; }
}