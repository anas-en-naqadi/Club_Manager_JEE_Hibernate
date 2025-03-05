package com.example.clubManager.dao;

import org.hibernate.Session;

import com.example.clubManager.models.Utilisateur;

import java.util.List;
public class UtilisateurDao {

    public void saveUtilisateur(Session session, Utilisateur utilisateur) {
        session.persist(utilisateur);
    }

    public Utilisateur getUtilisateurById(Session session, int id) {
        return session.get(Utilisateur.class, id);
    }

    public List<Utilisateur> getAllUtilisateurs(Session session) {
        return session.createQuery("from Utilisateur", Utilisateur.class).list();
    }

    public void updateUtilisateur(Session session, Utilisateur utilisateur) {
        session.merge(utilisateur);
    }

    public void deleteUtilisateur(Session session, int id) {
        Utilisateur utilisateur = getUtilisateurById(session, id);
        if (utilisateur != null) {
            session.remove(utilisateur);
        }
    }
}