package com.example.clubManager.services;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.example.clubManager.dao.UtilisateurDao;
import com.example.clubManager.models.Utilisateur;

import java.util.List;

public class UtilisateurService {
    private final SessionFactory sessionFactory;

    public UtilisateurService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveUtilisateur(Utilisateur utilisateur) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                UtilisateurDao dao = new UtilisateurDao();
                dao.saveUtilisateur(session, utilisateur);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }

    public Utilisateur getUtilisateurById(int id) {
        try (Session session = sessionFactory.openSession()) {
            UtilisateurDao dao = new UtilisateurDao();
            return dao.getUtilisateurById(session, id);
        }
    }

    public List<Utilisateur> getAllUtilisateurs() {
        try (Session session = sessionFactory.openSession()) {
            UtilisateurDao dao = new UtilisateurDao();
            return dao.getAllUtilisateurs(session);
        }
    }

    public void updateUtilisateur(Utilisateur utilisateur) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                UtilisateurDao dao = new UtilisateurDao();
                dao.updateUtilisateur(session, utilisateur);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }

    public void deleteUtilisateur(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                UtilisateurDao dao = new UtilisateurDao();
                dao.deleteUtilisateur(session, id);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }
    
    
    
}