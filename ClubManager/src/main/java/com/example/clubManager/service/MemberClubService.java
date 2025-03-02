package com.example.clubManager.service;

import javax.persistence.EntityManager;

import com.example.clubManager.dao.MemberClubDao;
import com.example.clubManager.model.MemberClub;

import java.util.List;

public class MemberClubService {

    private MemberClubDao memberClubDao;

    public MemberClubService(EntityManager entityManager) {
        this.memberClubDao = new MemberClubDao(entityManager);
    }

    // Ajouter un membre à un club
    public void addMemberToClub(int memberId, int clubId) {
        MemberClub memberClub = new MemberClub();
//        memberClub.setIdMembre(memberId);
//        memberClub.setIdClub(clubId);
        memberClubDao.addMemberToClub(memberClub);
    }

    // Récupérer tous les clubs auxquels un membre appartient
//    public List<MemberClub> getClubsByMember(int memberId) {
//        return memberClubDao.getClubsByMember(memberId);
//    }

    // Supprimer un membre d'un club
    public void removeMemberFromClub(int memberId, int clubId) {
        memberClubDao.removeMemberFromClub(memberId, clubId);
    }

    // Vérifier si un membre appartient à un club
//    public boolean isMemberInClub(int memberId, int clubId) {
//        return memberClubDao.isMemberInClub(memberId, clubId);
//    }
}

