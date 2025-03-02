package com.example.clubManager.service;

import javax.persistence.EntityManager;

import com.example.clubManager.dao.MemberEventDao;
import com.example.clubManager.model.MemberEvent;

import java.util.List;

public class MemberEventService {

    private MemberEventDao memberEventDao;

    public MemberEventService(EntityManager entityManager) {
        this.memberEventDao = new MemberEventDao(entityManager);
    }

//    // Ajouter un membre à un événement
//    public void addMemberToEvent(int memberId, int eventId) {
//        MemberEvent memberEvent = new MemberEvent();
//        memberEvent.setIdMembre(memberId);
//        memberEvent.setIdEvenement(eventId);
//        memberEventDao.addMemberToEvent(memberEvent);
//    }
//
//    // Récupérer tous les membres inscrits à un événement
//    public List<MemberEvent> getMembersByEvent(int eventId) {
//        return memberEventDao.getMembersByEvent(eventId);
//    }
//
//    // Supprimer un membre d'un événement
//    public void removeMemberFromEvent(int memberId, int eventId) {
//        memberEventDao.removeMemberEvent(memberId, eventId);
//    }
//
//    // Vérifier si un membre est inscrit à un événement
//    public boolean isMemberEnrolled(int memberId, int eventId) {
//        return memberEventDao.isMemberEnrolled(memberId, eventId);
//    }
}

