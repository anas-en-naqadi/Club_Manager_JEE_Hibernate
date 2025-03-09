package com.example.clubManager.services;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.example.clubManager.dao.MembreClubDao;
import com.example.clubManager.models.MembreClub;
import com.example.clubManager.models.MembreClubId;

import java.util.List;

public class MembreClubService {
    private final SessionFactory sessionFactory;

    public MembreClubService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveMembreClub(MembreClub membreClub) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                MembreClubDao dao = new MembreClubDao();
                dao.saveMembreClub(session, membreClub);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }

    public MembreClub getMembreClubById(MembreClubId id) {
        try (Session session = sessionFactory.openSession()) {
            MembreClubDao dao = new MembreClubDao();
            return dao.getMembreClubById(session, id);
        }
    }

    public List<MembreClub> getAllMembreClubs() {
        try (Session session = sessionFactory.openSession()) {
            MembreClubDao dao = new MembreClubDao();
            return dao.getAllMembreClubs(session);
        }
    }

    public void updateMembreClub(MembreClub membreClub) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                MembreClubDao dao = new MembreClubDao();
                dao.updateMembreClub(session, membreClub);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }

    public void deleteMembreClub(MembreClubId id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                MembreClubDao dao = new MembreClubDao();
                dao.deleteMembreClub(session, id);
                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw e;
            }
        }
    }

    public List<MembreClub> getMembreClubsByClub(int clubId) {
        try (Session session = sessionFactory.openSession()) {
            MembreClubDao dao = new MembreClubDao();
            return dao.getMembreClubsByClub(session, clubId);
        }
    }

    public List<MembreClub> getMembreClubsByMembre(int membreId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                "FROM MembreClub mc WHERE mc.idMembre = :membreId", MembreClub.class)
                .setParameter("membreId", membreId)
                .list();
        }
    }
    
    
    
    
    public boolean isMember(int clubId, int etudiantId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "SELECT COUNT(m) FROM MembreClub m " +
                    "WHERE m.idClub = :clubId AND m.idMembre = :etudiantId", Long.class)
                    .setParameter("clubId", clubId)
                    .setParameter("etudiantId", etudiantId)
                    .getSingleResult() > 0;
        }
    }
    
    

    
}