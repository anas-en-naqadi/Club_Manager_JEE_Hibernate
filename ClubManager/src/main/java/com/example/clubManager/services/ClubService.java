package com.example.clubManager.services;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.example.clubManager.dao.ClubDao;
import com.example.clubManager.models.Club;
import com.example.clubManager.models.Etudiant;
import com.example.clubManager.models.MembreClub;
import com.example.clubManager.util.HibernateUtil;

import java.util.List;

public class ClubService {
    private final SessionFactory sessionFactory;

    public ClubService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveClub(Club club) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                ClubDao dao = new ClubDao();
                dao.saveClub(session, club);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }

    public Club getClubById(int id) {
        try (Session session = sessionFactory.openSession()) {
            ClubDao dao = new ClubDao();
            Club club = dao.getClubById(session, id);

            if (club != null) {
                // Force initialize the membres collection
                Hibernate.initialize(club.getMembres());
                // Force initialize the evenements collection if needed
                Hibernate.initialize(club.getEvenements());
            }

            return club;
        }
    }
    

    
    public List<Club> getAllClubs() {
        try (Session session = sessionFactory.openSession()) {
            ClubDao dao = new ClubDao();
            return dao.getAllClubs(session);
        }
    }

    public void updateClub(Club club) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                ClubDao dao = new ClubDao();
                dao.updateClub(session, club);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }

    public void deleteClub(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                ClubDao dao = new ClubDao();
                dao.deleteClub(session, id);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }

    public List<Club> getClubsByPresident(int presidentId) {
        try (Session session = sessionFactory.openSession()) {
            ClubDao dao = new ClubDao();
            return dao.getClubsByPresident(session, presidentId);
        }
    }

    public List<Club> getClubsByAdmin(int adminId) {
        try (Session session = sessionFactory.openSession()) {
            ClubDao dao = new ClubDao();
            return dao.getClubsByAdmin(session, adminId);
        }
    }
    
    
    
    public int getMemberCount(int clubId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                "SELECT count(m) FROM Club c JOIN c.membres m WHERE c.idClub = :clubId", 
                Long.class)
                .setParameter("clubId", clubId)
                .getSingleResult()
                .intValue();
        }
    }
    
    
    public Club getClubWithEvents(int id) {
        try (Session session = sessionFactory.openSession()) {
            ClubDao dao = new ClubDao();
            Club club = dao.getClubById(session, id);

            if (club != null) {
                Hibernate.initialize(club.getEvenements()); // Force load events before closing session
            }

            return club;
        }
    }

    
    
    
    public Club getClubByIdWithMembers(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "SELECT c FROM Club c " +
                    "LEFT JOIN FETCH c.membres " +
                    "WHERE c.idClub = :id", Club.class)
                    .setParameter("id", id)
                    .uniqueResult();
        }
    }
    
    
    
    public void addMemberToClub(Club club, Etudiant etudiant) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                // Reattach entities if necessary
                Club managedClub = session.contains(club) ? club : session.merge(club);
                Etudiant managedEtudiant = session.contains(etudiant) ? etudiant : session.merge(etudiant);

                // Check if membership exists
                boolean exists = managedClub.getMembres().stream()
                    .anyMatch(m -> m.getEtudiant().getIdEtudiant() == managedEtudiant.getIdEtudiant());
                
                if (!exists) {
                    MembreClub membership = new MembreClub(managedEtudiant, managedClub);
                    session.persist(membership);
                    
                    // Update both sides of the relationship
                    managedClub.getMembres().add(membership);
                    managedEtudiant.getMemberships().add(membership);
                }
                
                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw e;
            }
        }
    }
    
    
    
    
    
    
    public static List<Club> getPopularClubsByMembers(int maxResults) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "SELECT DISTINCT c FROM Club c LEFT JOIN FETCH c.membres " +
                    "ORDER BY SIZE(c.membres) DESC", Club.class)
                    .setMaxResults(maxResults)
                    .list();
        }
    }    
    
    
    
    
    
    
    
    
    
    
    
}