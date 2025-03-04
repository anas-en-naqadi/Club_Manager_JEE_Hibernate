package com.example.clubManager.dao;

import org.hibernate.Session;


import com.example.clubManager.model.Evenement;
import java.util.List;
public class EvenementDao {

    public void saveEvenement(Session session, Evenement even) {
        session.persist(even);
    }

    public Evenement getEvenementById(Session session, int id) {
        return session.get(Evenement.class, id);
    }

    public List<Evenement> getAllEvenements(Session session) {
        return session.createQuery("from Evenement", Evenement.class).list();
    }

    public void updateEvenement(Session session, Evenement even) {
        session.merge(even);
    }

    public void deleteEvenement(Session session, int id) {
        Evenement even = getEvenementById(session, id);
        if (even != null) {
            session.remove(even);
        }
    }

    /** Returns events organized by the specified club. */
    public List<Evenement> getEvenementsByClub(Session session, int clubId) {
        return session.createQuery("from Evenement where club.idClub = :clubId", Evenement.class)
                      .setParameter("clubId", clubId)
                      .list();
    }
}