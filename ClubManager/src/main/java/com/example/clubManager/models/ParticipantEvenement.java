package com.example.clubManager.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "participant_evenement")
@IdClass(ParticipantEvenementId.class)
public class ParticipantEvenement {

    @Id
    @Column(name = "ID_PARTICIPANT")
    private int idParticipant;

    @Id
    @Column(name = "ID_EVENEMENT")
    private int idEvenement;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_INSCRIPTION", nullable = false)
    private Date dateInscription = new Date();

    @ManyToOne
    @JoinColumn(name = "ID_PARTICIPANT", insertable = false, updatable = false)
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "ID_EVENEMENT", insertable = false, updatable = false)
    private Evenement evenement;

    // Constructors
    public ParticipantEvenement() {}

    public ParticipantEvenement(Etudiant etudiant, Evenement evenement) {
        this.etudiant = etudiant;
        this.evenement = evenement;
        this.idParticipant = etudiant.getIdEtudiant();
        this.idEvenement = evenement.getIdEvenement();
    }

    @PrePersist
    protected void onCreate() {
        this.dateInscription = new Date();
    }

    // Getters and Setters
    public int getIdParticipant() { return idParticipant; }
    public void setIdParticipant(int idParticipant) { this.idParticipant = idParticipant; }
    public int getIdEvenement() { return idEvenement; }
    public void setIdEvenement(int idEvenement) { this.idEvenement = idEvenement; }
    public Date getDateInscription() { return dateInscription; }
    public void setDateInscription(Date dateInscription) { this.dateInscription = dateInscription; }
    public Etudiant getEtudiant() { return etudiant; }
    public void setEtudiant(Etudiant etudiant) { this.etudiant = etudiant; }
    public Evenement getEvenement() { return evenement; }
    public void setEvenement(Evenement evenement) { this.evenement = evenement; }
    
    
    
    
    
    
    
}