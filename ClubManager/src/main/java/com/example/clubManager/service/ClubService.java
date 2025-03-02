package com.example.clubManager.service;

import javax.persistence.EntityManager;
import com.example.clubManager.dao.ClubDao;
import com.example.clubManager.model.Club;

import java.util.List;

public class ClubService {

    private ClubDao clubDao;
    private EntityManager entityManager;

    public ClubService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.clubDao = new ClubDao(entityManager);  // Passer l'EntityManager au DAO
    }

    // Ajouter un club
    public void addClub(Club club) {
        entityManager.getTransaction().begin();  // Démarrer une transaction
        try {
            clubDao.addClub(club); // Ajouter le club via le DAO
            entityManager.getTransaction().commit();  // Commiter la transaction
        } catch (Exception e) {
            entityManager.getTransaction().rollback();  // En cas d'erreur, rollback
            throw new RuntimeException("Erreur lors de l'ajout du club", e);
        }
    }

    // Récupérer un club par son ID
    public Club getClubById(int id) {
        return clubDao.getClubById(id);
    }

    // Récupérer tous les clubs
    public List<Club> getAllClubs() {
        return clubDao.getAllClubs();
    }

    // Mettre à jour un club
    public void updateClub(Club club) {
        entityManager.getTransaction().begin();
        try {
            clubDao.updateClub(club);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Erreur lors de la mise à jour du club", e);
        }
    }

    // Supprimer un club
    public void deleteClub(int id) {
        entityManager.getTransaction().begin();
        try {
            clubDao.deleteClub(id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Erreur lors de la suppression du club", e);
        }
    }
}
