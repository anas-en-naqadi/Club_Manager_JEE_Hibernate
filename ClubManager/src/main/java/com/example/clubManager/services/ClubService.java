package com.example.clubManager.services;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.example.clubManager.dao.ClubDao;
import com.example.clubManager.models.Club;

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
            return dao.getClubById(session, id);
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
}