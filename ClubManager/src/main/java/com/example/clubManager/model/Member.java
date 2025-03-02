	package com.example.clubManager.model;
	
	import javax.persistence.*;
	import java.util.Date;
	import java.util.HashSet;
	import java.util.Set;
	
	@Entity
	@Table(name = "MEMBRE")
	public class Member {
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "ID_MEMBRE")
	    private int id;
	
	    @Column(name = "CNE", nullable = false, unique = true, length = 40)
	    private String cne;
	
	    @Column(name = "NOM_COMPLET", nullable = false, length = 50)
	    private String nomComplet;
	
	    @Column(name = "ROLE", nullable = false, length = 100)
	    private String role;
	
	    @Column(name = "EMAIL", nullable = false, unique = true, length = 100)
	    private String email;
	
	    @Column(name = "MOT_DE_PASSE", nullable = false, length = 255)
	    private String motDePasse;
	
	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "DATE_INSCRIPTION", nullable = false, updatable = false)
	    private Date dateInscription = new Date();
	
	
	    @ManyToMany
	    @JoinTable(
	        name = "MEMBRE_CLUB",
	        joinColumns = @JoinColumn(name = "ID_MEMBRE"),
	        inverseJoinColumns = @JoinColumn(name = "ID_CLUB")
	    )
	    private Set<Club> clubs = new HashSet<>();
	
	  
	    @ManyToMany
	    @JoinTable(
	        name = "MEMBRE_EVENEMENT",
	        joinColumns = @JoinColumn(name = "ID_MEMBRE"),
	        inverseJoinColumns = @JoinColumn(name = "ID_EVENEMENT")
	    )
	    private Set<Event> evenements = new HashSet<>();
	
	    
	    public Member() {
	    	 
	    }
	
	  
	    public Member(String cne, String nomComplet, String role, String email, String motDePasse) {
	        this.cne = cne;
	        this.nomComplet = nomComplet;
	        this.role = role;
	        this.email = email;
	        this.motDePasse = motDePasse;

	    }
	    
	    @PrePersist
	    protected void onCreate() {
	        this.dateInscription = new Date();
	    }
	
	    // ✅ Getters & Setters
	    public int getIdMember() {
	        return id;
	    }
	
	    public void setId(int id) {
	        this.id = id;
	    }
	
	    public String getCne() {
	        return cne;
	    }
	
	    public void setCne(String cne) {
	        this.cne = cne;
	    }
	
	    public String getNomComplet() {
	        return nomComplet;
	    }
	
	    public void setNomComplet(String nomComplet) {
	        this.nomComplet = nomComplet;
	    }
	
	    public String getRole() {
	        return role;
	    }
	
	    public void setRole(String role) {
	        this.role = role;
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
	
	    public Date getDateInscription() {
	        return dateInscription;
	    }
	
	    public void setDateInscription(Date dateInscription) {
	        this.dateInscription = dateInscription;
	    }
	
	    public Set<Club> getClubs() {
	        return clubs;
	    }
	
	    public void setClubs(Set<Club> clubs) {
	        this.clubs = clubs;
	    }
	
	    public Set<Event> getEvenements() {
	        return evenements;
	    }
	
	    public void setEvenements(Set<Event> evenements) {
	        this.evenements = evenements;
	    }
	
	    // ✅ toString() pour debug
	    @Override
	    public String toString() {
	        return "Membre{" +
	                "id=" + id +
	                ", cne='" + cne + '\'' +
	                ", nomComplet='" + nomComplet + '\'' +
	                ", role='" + role + '\'' +
	                ", email='" + email + '\'' +
	                ", dateInscription=" + dateInscription +
	                '}';
	    }
	}
