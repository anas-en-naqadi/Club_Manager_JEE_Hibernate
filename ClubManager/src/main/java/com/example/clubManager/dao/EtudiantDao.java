package com.example.clubManager.dao;

import org.hibernate.Session;


import com.example.clubManager.models.Etudiant;


import java.util.List;
public class EtudiantDao {

    public void saveEtudiant(Session session, Etudiant etudiant) {
        session.persist(etudiant);
    }

    public Etudiant getEtudiantById(Session session, int id) {
        return session.get(Etudiant.class, id);
    }

    public List<Etudiant> getAllEtudiants(Session session) {
        return session.createQuery("from Etudiant", Etudiant.class).list();
    }

    public void updateEtudiant(Session session, Etudiant etudiant) {
        session.merge(etudiant);
    }

    public void deleteEtudiant(Session session, int id) {
        Etudiant etudiant = getEtudiantById(session, id);
        if (etudiant != null) {
            session.remove(etudiant);
        }
    }
}