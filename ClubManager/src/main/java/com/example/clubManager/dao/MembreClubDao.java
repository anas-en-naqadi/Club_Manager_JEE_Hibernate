package com.example.clubManager.dao;

import org.hibernate.Session;

import com.example.clubManager.model.MembreClub;
import com.example.clubManager.model.MembreClubId;

import java.util.List;
public class MembreClubDao {

    public void saveMembreClub(Session session, MembreClub membreClub) {
        session.persist(membreClub);
    }

    public MembreClub getMembreClubById(Session session, MembreClubId id) {
        return session.get(MembreClub.class, id);
    }

    public List<MembreClub> getAllMembreClubs(Session session) {
        return session.createQuery("from MembreClub", MembreClub.class).list();
    }

    public void updateMembreClub(Session session, MembreClub membreClub) {
        session.merge(membreClub);
    }

    public void deleteMembreClub(Session session, MembreClubId id) {
        MembreClub membreClub = getMembreClubById(session, id);
        if (membreClub != null) {
            session.remove(membreClub);
        }
    }

    /** Returns all memberships for a specific club. */
    public List<MembreClub> getMembreClubsByClub(Session session, int clubId) {
        return session.createQuery("from MembreClub where idClub = :clubId", MembreClub.class)
                      .setParameter("clubId", clubId)
                      .list();
    }

    /** Returns all clubs a specific member belongs to. */
    public List<MembreClub> getMembreClubsByMembre(Session session, int membreId) {
        return session.createQuery("from MembreClub where idMembre = :membreId", MembreClub.class)
                      .setParameter("membreId", membreId)
                      .list();
    }
}