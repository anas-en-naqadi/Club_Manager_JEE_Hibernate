package com.example.clubManager.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.example.clubManager.model.Club;


import java.util.List;

public class ClubDao {

    private EntityManager entityManager;

    public ClubDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Ajouter un club
    public void addClub(Club club) {
        entityManager.persist(club);
    }
  

    // Récupérer un club par son ID
    public Club getClubById(int id) {
        return entityManager.find(Club.class, id);
    }

    // Récupérer tous les clubs
    public List<Club> getAllClubs() {
        TypedQuery<Club> query = entityManager.createQuery("SELECT c FROM Club c", Club.class);
        return query.getResultList();
    }

    // Mettre à jour un club
    public void updateClub(Club club) {
        entityManager.merge(club);
    }

    // Supprimer un club
    public void deleteClub(int id) {
        Club club = getClubById(id);
        if (club != null) {
            entityManager.remove(club);
        }
    }
}

