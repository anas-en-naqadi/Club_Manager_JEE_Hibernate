package com.example.clubManager.dao;

import javax.persistence.EntityManager;

import com.example.clubManager.model.Member;
import com.example.clubManager.model.MemberClub;

import java.util.List;

public class MemberClubDao {

    private EntityManager entityManager;

    public MemberClubDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Ajouter un membre à un club
    public void addMemberToClub(MemberClub memberClub) {
        entityManager.persist(memberClub);
    }

    // Récupérer les membres d'un club
    public List<Member> getMembersForClub(int clubId) {
        return entityManager.createQuery(
                "SELECT m FROM Member m JOIN m.clubs c WHERE c.id = :clubId", Member.class)
                .setParameter("clubId", clubId)
                .getResultList();
    }

    // Supprimer un membre d'un club
    public void removeMemberFromClub(int memberId, int clubId) {
        MemberClub memberClub = entityManager.createQuery(
                "SELECT mc FROM MemberClub mc WHERE mc.member.id = :memberId AND mc.club.id = :clubId", MemberClub.class)
                .setParameter("memberId", memberId)
                .setParameter("clubId", clubId)
                .getSingleResult();
        
        if (memberClub != null) {
            entityManager.remove(memberClub);
        }
    }
}
