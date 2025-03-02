package com.example.clubManager.service;

import javax.persistence.EntityManager;

import com.example.clubManager.dao.EventDao;
import com.example.clubManager.model.Event;

import java.util.List;

public class EventService {

    private EventDao eventDao;

    public EventService(EntityManager entityManager) {
        this.eventDao = new EventDao(entityManager);
    }

    // Ajouter un événement
    public void addEvent(Event event) {
        if (event != null && event.getNom() != null && !event.getNom().isEmpty()) {
            eventDao.addEvent(event);
        } else {
            throw new IllegalArgumentException("Le nom de l'événement est requis.");
        }
    }

    // Récupérer un événement par son ID
    public Event getEventById(int id) {
        Event event = eventDao.getEventById(id);
        if (event == null) {
            throw new IllegalArgumentException("Aucun événement trouvé avec l'ID: " + id);
        }
        return event;
    }

    // Récupérer tous les événements d'un club
    public List<Event> getEventsByClub(int clubId) {
        return eventDao.getEventsByClub(clubId);
    }

    // Supprimer un événement
    public void deleteEvent(int id) {
        Event event = eventDao.getEventById(id);
        if (event != null) {
            eventDao.deleteEvent(id);
        } else {
            throw new IllegalArgumentException("Aucun événement trouvé avec l'ID: " + id);
        }
    }

    // Mettre à jour un événement
    public void updateEvent(Event event) {
        if (event != null && event.getIdEvenement() > 0) {
            eventDao.updateEvent(event);
        } else {
            throw new IllegalArgumentException("L'événement doit avoir un ID valide.");
        }
    }
}
