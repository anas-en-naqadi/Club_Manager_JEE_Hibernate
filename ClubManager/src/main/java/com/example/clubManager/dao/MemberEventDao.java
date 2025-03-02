package com.example.clubManager.dao;
import javax.persistence.EntityManager;

import com.example.clubManager.model.Member;
import com.example.clubManager.model.MemberEvent;

import java.util.List;

public class MemberEventDao {

    private EntityManager entityManager;

    public MemberEventDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Ajouter un membre à un événement
    public void addMemberToEvent(MemberEvent memberEvent) {
        entityManager.persist(memberEvent);
    }

    // Récupérer les membres d'un événement
    public List<Member> getMembersForEvent(int eventId) {
        return entityManager.createQuery(
                "SELECT m FROM Member m JOIN m.events e WHERE e.id = :eventId", Member.class)
                .setParameter("eventId", eventId)
                .getResultList();
    }
}
