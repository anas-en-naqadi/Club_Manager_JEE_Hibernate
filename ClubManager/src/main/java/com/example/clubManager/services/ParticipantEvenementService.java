package com.example.clubManager.services;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.example.clubManager.dao.ParticipantEvenementDao;
import com.example.clubManager.models.ParticipantEvenement;
import com.example.clubManager.models.ParticipantEvenementId;

import java.util.List;

public class ParticipantEvenementService {
    private final SessionFactory sessionFactory;

    public ParticipantEvenementService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveParticipantEvenement(ParticipantEvenement participantEvenement) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                ParticipantEvenementDao dao = new ParticipantEvenementDao();
                dao.saveParticipantEvenement(session, participantEvenement);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }

    public ParticipantEvenement getParticipantEvenementById(ParticipantEvenementId id) {
        try (Session session = sessionFactory.openSession()) {
            ParticipantEvenementDao dao = new ParticipantEvenementDao();
            return dao.getParticipantEvenementById(session, id);
        }
    }

    public List<ParticipantEvenement> getAllParticipantEvenements() {
        try (Session session = sessionFactory.openSession()) {
            ParticipantEvenementDao dao = new ParticipantEvenementDao();
            return dao.getAllParticipantEvenements(session);
        }
    }

    public void updateParticipantEvenement(ParticipantEvenement participantEvenement) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                ParticipantEvenementDao dao = new ParticipantEvenementDao();
                dao.updateParticipantEvenement(session, participantEvenement);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }

    public void deleteParticipantEvenement(ParticipantEvenementId id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                ParticipantEvenementDao dao = new ParticipantEvenementDao();
                dao.deleteParticipantEvenement(session, id);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }

    public List<ParticipantEvenement> getParticipantEvenementsByEvenement(int evenId) {
        try (Session session = sessionFactory.openSession()) {
            ParticipantEvenementDao dao = new ParticipantEvenementDao();
            return dao.getParticipantEvenementsByEvenement(session, evenId);
        }
    }

    public List<ParticipantEvenement> getParticipantEvenementsByParticipant(int participantId) {
        try (Session session = sessionFactory.openSession()) {
            ParticipantEvenementDao dao = new ParticipantEvenementDao();
            return dao.getParticipantEvenementsByParticipant(session, participantId);
        }
    }
    
    
    
    
    
    public void deleteByClubAndEtudiant(int clubId, int etudiantId) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            
            String hql = "delete from ParticipantEvenement pe " +
                         "where pe.etudiant.idEtudiant = :etudiantId " +
                         "and pe.evenement.club.id = :clubId";
            
            session.createMutationQuery(hql)
                .setParameter("etudiantId", etudiantId)
                .setParameter("clubId", clubId)
                .executeUpdate();
            
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
    
    
    
    
}