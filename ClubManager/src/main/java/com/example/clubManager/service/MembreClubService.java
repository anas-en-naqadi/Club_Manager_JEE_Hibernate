package com.example.clubManager.service;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.example.clubManager.dao.MembreClubDao;
import com.example.clubManager.model.MembreClub;
import com.example.clubManager.model.MembreClubId;

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
                tx.rollback();
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
            MembreClubDao dao = new MembreClubDao();
            return dao.getMembreClubsByMembre(session, membreId);
        }
    }
}