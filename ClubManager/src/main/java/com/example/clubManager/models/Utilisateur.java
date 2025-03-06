package com.example.clubManager.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_UTILISATEUR")
    private Integer id;
    
    @Column(name = "EMAIL", nullable = false, unique = true, length = 100)
    private String email;
    
    @Column(name = "MOT_DE_PASSE", nullable = false, length = 255)
    private String motDePasse;
    
    @Column(name = "ROLE", nullable = false, length = 50)
    private String role;
    
    @OneToOne(mappedBy = "utilisateur", cascade = CascadeType.ALL, orphanRemoval = true)
    private Etudiant etudiant;
    
    // One-to-Many relationship with Club
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private Set<Club> clubs = new HashSet<>();
    
    public Utilisateur() {

    }
    public Utilisateur(String e,String mp,String r) {
    	email = e;
    	motDePasse = mp;
    	role = r;
    }
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMotDePasse() {
        return motDePasse;
    }
    
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public Etudiant getEtudiant() {
        return etudiant;
    }
    
    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
    
    public Set<Club> getClubs() {
        return clubs;
    }
    
    public void setClubs(Set<Club> clubs) {
        this.clubs = clubs;
    }
}