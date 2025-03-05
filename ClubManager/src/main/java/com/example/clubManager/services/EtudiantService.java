package com.example.clubManager.services;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.example.clubManager.dao.EtudiantDao;
import com.example.clubManager.models.Etudiant;

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
            return dao.getEtudiantById(session, id);
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
                EtudiantDao dao = new EtudiantDao();
                dao.deleteEtudiant(session, id);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }
}