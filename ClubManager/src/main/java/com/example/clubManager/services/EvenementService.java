package com.example.clubManager.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.example.clubManager.models.Etudiant;
import com.example.clubManager.models.Evenement;



import java.util.*;

public class EvenementService {
    
    private final SessionFactory sessionFactory;

    public EvenementService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Evenement> getAllEvenementsWithDetails() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                "SELECT DISTINCT e FROM Evenement e " +
                "LEFT JOIN FETCH e.club " +
                "LEFT JOIN FETCH e.participants", Evenement.class)
                .list();
        }
    }

    public void saveEvenement(Evenement evenement) {
        try (Session session = sessionFactory.openSession()) {
            org.hibernate.Transaction tx = session.beginTransaction();
            try {
                session.persist(evenement);
                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw new RuntimeException("Error saving event", e);
            }
        }
    }

    public void updateEvenement(Evenement evenement) {
        try (Session session = sessionFactory.openSession()) {
        	org.hibernate.Transaction tx = session.beginTransaction();
        	try {
                session.merge(evenement);
                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw new RuntimeException("Error updating event", e);
            }
        }
    }

    public void deleteEvenement(int id) {
        try (Session session = sessionFactory.openSession()) {
            org.hibernate.Transaction tx = session.beginTransaction();
            try {
                Evenement event = session.createQuery(
                        "SELECT e FROM Evenement e " +
                        "LEFT JOIN FETCH e.participants " +
                        "LEFT JOIN FETCH e.club " +
                        "WHERE e.idEvenement = :id", Evenement.class)
                        .setParameter("id", id)
                        .uniqueResult();

                if (event != null) {
                    // Clear relationships properly
                    for (Etudiant participant : new ArrayList<>(event.getParticipants())) {
                        participant.getEvenements().remove(event);
                        // No need to call update() - Hibernate tracks changes automatically
                    }
                    
                    event.getParticipants().clear();
                    
                    if (event.getClub() != null) {
                        event.getClub().getEvenements().remove(event);
                    }
                    
                    session.remove(event);
                }
                tx.commit();
            } catch (Exception e) {
                if (tx != null && tx.isActive()) tx.rollback();
                throw new RuntimeException("Error deleting event", e);
            }
        }
    }
    
    
    
    
    public Evenement getEvenementById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                "SELECT e FROM Evenement e " +
                "LEFT JOIN FETCH e.club " +
                "WHERE e.idEvenement = :id", Evenement.class)
                .setParameter("id", id)
                .uniqueResult();
        }
    }



}