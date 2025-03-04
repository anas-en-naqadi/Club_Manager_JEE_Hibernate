package com.example.clubManager.service;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.example.clubManager.dao.EvenementDao;
import com.example.clubManager.model.Evenement;

import java.util.List;

public class EvenementService {
    private final SessionFactory sessionFactory;

    public EvenementService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveEvenement(Evenement even) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                EvenementDao dao = new EvenementDao();
                dao.saveEvenement(session, even);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }

    public Evenement getEvenementById(int id) {
        try (Session session = sessionFactory.openSession()) {
            EvenementDao dao = new EvenementDao();
            return dao.getEvenementById(session, id);
        }
    }

    public List<Evenement> getAllEvenements() {
        try (Session session = sessionFactory.openSession()) {
            EvenementDao dao = new EvenementDao();
            return dao.getAllEvenements(session);
        }
    }

    public void updateEvenement(Evenement even) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                EvenementDao dao = new EvenementDao();
                dao.updateEvenement(session, even);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }

    public void deleteEvenement(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                EvenementDao dao = new EvenementDao();
                dao.deleteEvenement(session, id);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }

    public List<Evenement> getEvenementsByClub(int clubId) {
        try (Session session = sessionFactory.openSession()) {
            EvenementDao dao = new EvenementDao();
            return dao.getEvenementsByClub(session, clubId);
        }
    }
}