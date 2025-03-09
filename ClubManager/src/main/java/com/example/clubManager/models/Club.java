package com.example.clubManager.models;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "club")


public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLUB")
    private int idClub;

    @Column(name = "NOM", nullable = false, length = 100)
    private String nom;

    @Column(name = "DESCRIPTION")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CREATION", nullable = false, updatable = false)
    private Date dateCreation = new Date();

    @ManyToOne
    @JoinColumn(name = "PRESIDENT_ID", nullable = false)
    private Etudiant president;

    @ManyToOne
    @JoinColumn(name = "ID_UTILISATEUR", nullable = false)
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Set<Evenement> evenements = new HashSet<>();

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MembreClub> membres = new HashSet<>();

    
    
    @Column(name = "IMAGE", columnDefinition = "LONGBLOB")
    private byte[] image;

    
    
    
    
    
    // Constructors
    public Club() {}

    public Club(String nom, String description, Etudiant president, Utilisateur utilisateur) {
        this.nom = nom;
        this.description = description;
        this.president = president;
        this.utilisateur = utilisateur;
    }

    @PrePersist
    protected void onCreate() {
        this.dateCreation = new Date();
    }

    // Getters and Setters
    public int getIdClub() { return idClub; }
    public void setIdClub(int idClub) { this.idClub = idClub; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Date getDateCreation() { return dateCreation; }
    public void setDateCreation(Date dateCreation) { this.dateCreation = dateCreation; }
    public Etudiant getPresident() { return president; }
    public void setPresident(Etudiant president) { this.president = president; }
    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }
    public Set<Evenement> getEvenements() { return evenements; }
    public void setEvenements(Set<Evenement> evenements) { this.evenements = evenements; }
    public Set<MembreClub> getMembres() { return membres; }
    public void setMembres(Set<MembreClub> membres) { this.membres = membres; }
    
    
    public byte[] getImage() { return image; }
    public void setImage(byte[] image) { this.image = image; }
    
    
    


    // Helper method
    public void addMembre(Etudiant etudiant) {
        MembreClub membreClub = new MembreClub(etudiant, this);
        this.membres.add(membreClub);
        etudiant.getMemberships().add(membreClub);
    }
    
}