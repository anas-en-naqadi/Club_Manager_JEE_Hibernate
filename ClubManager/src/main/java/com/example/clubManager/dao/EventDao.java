package com.example.clubManager.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.example.clubManager.model.Event;

import java.util.ArrayList;
import java.util.List;

public class EventDao {

    private EntityManager entityManager;

    public EventDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Ajouter un événement
    public void addEvent(Event event) {
        entityManager.persist(event);
    }

    // Récupérer un événement par son ID
    public Event getEventById(int id) {
        return entityManager.find(Event.class, id);
    }
    
    
    public List<Event> getEventsByClub(int id){
    	return new ArrayList<Event>();
    }

    // Récupérer tous les événements
    public List<Event> getAllEvents() {
        TypedQuery<Event> query = entityManager.createQuery("SELECT e FROM Event e", Event.class);
        return query.getResultList();
    }

    // Mettre à jour un événement
    public void updateEvent(Event event) {
        entityManager.merge(event);
    }

    // Supprimer un événement
    public void deleteEvent(int id) {
        Event event = getEventById(id);
        if (event != null) {
            entityManager.remove(event);
        }
    }
}
