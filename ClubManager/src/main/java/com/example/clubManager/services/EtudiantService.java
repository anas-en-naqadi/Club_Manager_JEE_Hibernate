package com.example.clubManager.services;




import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.example.clubManager.dao.EtudiantDao;
import com.example.clubManager.models.Club;
import com.example.clubManager.models.Etudiant;
import com.example.clubManager.models.Evenement;
import com.example.clubManager.models.MembreClub;
import com.example.clubManager.models.Utilisateur;

import java.util.ArrayList;
import java.util.List;

public class EtudiantService {
    private final SessionFactory sessionFactory;

    public EtudiantService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveEtudiant(Etudiant etudiant) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                EtudiantDao dao = new EtudiantDao();
                dao.saveEtudiant(session, etudiant);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }

    public Etudiant getEtudiantById(int id) {
        try (Session session = sessionFactory.openSession()) {
            EtudiantDao dao = new EtudiantDao();
            Etudiant etudiant = dao.getEtudiantById(session, id);

            return etudiant;
        }
    }

    public List<Etudiant> getAllEtudiants() {
        try (Session session = sessionFactory.openSession()) {
            EtudiantDao dao = new EtudiantDao();
            return dao.getAllEtudiants(session);
        }
    }

    public void updateEtudiant(Etudiant etudiant) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                EtudiantDao dao = new EtudiantDao();
                dao.updateEtudiant(session, etudiant);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }

    public void deleteEtudiant(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                Etudiant student = session.get(Etudiant.class, id);
                
                // 1. Delete president clubs and their memberships
                for (Club club : new ArrayList<>(student.getClubsDiriges())) {
                    // Remove all memberships in the club
                    for (MembreClub membership : new ArrayList<>(club.getMembres())) {
                        session.remove(membership);
                    }
                    // Delete the club itself
                    session.remove(club);
                }
                
                // 2. Remove student memberships
                for (MembreClub membership : new ArrayList<>(student.getMemberships())) {
                    membership.getClub().getMembres().remove(membership);
                    session.remove(membership);
                }
                
                // 3. Remove from events
                for (Evenement event : new ArrayList<>(student.getEvenements())) {
                    event.getParticipants().remove(student);
                    session.merge(event);
                }
                
                // 4. Break user relationship
                if (student.getUtilisateur() != null) {
                    Utilisateur user = student.getUtilisateur();
                    // Break bidirectional relationship
                    user.setEtudiant(null);
                    session.merge(user);  // Update user state
                    session.remove(user);
                }
                
                // 5. Finally delete the student
                session.remove(student);
                
                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw e;
            }
        }
    }
    
    
    
    
    public Etudiant getEtudiantWithClubs(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "SELECT e FROM Etudiant e " +
                    "LEFT JOIN FETCH e.memberships " +
                    "WHERE e.idEtudiant = :id", Etudiant.class)
                    .setParameter("id", id)
                    .uniqueResult();
        }
    }
    
    public List<Etudiant> getAllEtudiantsWithClubs() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                "SELECT DISTINCT e FROM Etudiant e " +
                "LEFT JOIN FETCH e.memberships mc " +  // Changed from clubsMembre to memberships
                "LEFT JOIN FETCH mc.club " +
                "LEFT JOIN FETCH e.clubsDiriges", Etudiant.class)
                .list();
        }
    }
}