package com.example.clubManager.dao;

import org.hibernate.Session;


import com.example.clubManager.models.ParticipantEvenement;
import com.example.clubManager.models.ParticipantEvenementId;

import java.util.List;
public class ParticipantEvenementDao {

    public void saveParticipantEvenement(Session session, ParticipantEvenement participantEvenement) {
        session.persist(participantEvenement);
    }

    public ParticipantEvenement getParticipantEvenementById(Session session, ParticipantEvenementId id) {
        return session.get(ParticipantEvenement.class, id);
    }

    public List<ParticipantEvenement> getAllParticipantEvenements(Session session) {
        return session.createQuery("from ParticipantEvenement", ParticipantEvenement.class).list();
    }

    public void updateParticipantEvenement(Session session, ParticipantEvenement participantEvenement) {
        session.merge(participantEvenement);
    }

    public void deleteParticipantEvenement(Session session, ParticipantEvenementId id) {
        ParticipantEvenement participantEvenement = getParticipantEvenementById(session, id);
        if (participantEvenement != null) {
            session.remove(participantEvenement);
        }
    }

    /** Returns all participants for a specific event. */
    public List<ParticipantEvenement> getParticipantEvenementsByEvenement(Session session, int evenId) {
        return session.createQuery("from ParticipantEvenement where idEvenement = :evenId", ParticipantEvenement.class)
                      .setParameter("evenId", evenId)
                      .list();
    }

    /** Returns all events a specific participant is attending. */
    public List<ParticipantEvenement> getParticipantEvenementsByParticipant(Session session, int participantId) {
        return session.createQuery("from ParticipantEvenement where idParticipant = :participantId", ParticipantEvenement.class)
                      .setParameter("participantId", participantId)
                      .list();
    }
}