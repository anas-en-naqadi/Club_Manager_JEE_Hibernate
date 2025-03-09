package com.example.clubManager.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "etudiant")
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ETUDIANT")
    private int idEtudiant;

    @Column(name = "CNE", nullable = false, unique = true, length = 50)
    private String cne;

    @Column(name = "NOM_COMPLET", nullable = false, length = 50)
    private String nomComplet;

    @Column(name = "FILLIERE", nullable = false, length = 100)
    private String filliere;

    @Column(name = "FACULTE", nullable = false, length = 100)
    private String faculte;

    @OneToOne
    @JoinColumn(name = "ID_UTILISATEUR", referencedColumnName = "ID_UTILISATEUR")
    private Utilisateur utilisateur;
    
    @OneToMany(mappedBy = "president", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Club> clubsDiriges = new HashSet<>();

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MembreClub> memberships = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "participant_evenement",
        joinColumns = @JoinColumn(name = "ID_PARTICIPANT"),
        inverseJoinColumns = @JoinColumn(name = "ID_EVENEMENT")
    )
    private Set<Evenement> evenements = new HashSet<>();

    // Constructors
    public Etudiant() {}

    public Etudiant(String cne, String nomComplet, String filliere, String faculte, Utilisateur utilisateur) {
        this.cne = cne;
        this.nomComplet = nomComplet;
        this.filliere = filliere;
        this.faculte = faculte;
        this.utilisateur = utilisateur;
    }

    // Getters and Setters
    public int getIdEtudiant() { return idEtudiant; }
    public void setIdEtudiant(int idEtudiant) { this.idEtudiant = idEtudiant; }
    public String getCne() { return cne; }
    public void setCne(String cne) { this.cne = cne; }
    public String getNomComplet() { return nomComplet; }
    public void setNomComplet(String nomComplet) { this.nomComplet = nomComplet; }
    public String getFilliere() { return filliere; }
    public void setFilliere(String filliere) { this.filliere = filliere; }
    public String getFaculte() { return faculte; }
    public void setFaculte(String faculte) { this.faculte = faculte; }
    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }
    public Set<Club> getClubsDiriges() { return clubsDiriges; }
    public void setClubsDiriges(Set<Club> clubsDiriges) { this.clubsDiriges = clubsDiriges; }
    public Set<Evenement> getEvenements() { return evenements; }
    public void setEvenements(Set<Evenement> evenements) { this.evenements = evenements; }
    
    public Set<MembreClub> getMemberships() {
        return memberships;
    }

    public void setMemberships(Set<MembreClub> memberships) {
        this.memberships = memberships;
    }
    
    
    // Helper method
    public void joinClub(Club club) {
        MembreClub membership = new MembreClub(this, club);
        this.memberships.add(membership);
        club.getMembres().add(membership);
    }

    
    
    
    
    
    
    
    
}