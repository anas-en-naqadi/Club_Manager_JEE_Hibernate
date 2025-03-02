package com.example.clubManager.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.example.clubManager.model.Member;

import java.util.List;

public class MemberDao {

    private EntityManager entityManager;

    public MemberDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Ajouter un membre
    public void addMember(Member member) {
        entityManager.persist(member);
    }

    // Récupérer un membre par son ID
    public Member getMemberById(int id) {
        return entityManager.find(Member.class, id);
    }

    // Récupérer tous les membres
    public List<Member> getAllMembers() {
        TypedQuery<Member> query = entityManager.createQuery("SELECT m FROM Member m", Member.class);
        return query.getResultList();
    }

    // Mettre à jour un membre
    public void updateMember(Member member) {
        entityManager.merge(member);
    }

    // Supprimer un membre
    public void deleteMember(int id) {
        Member member = getMemberById(id);
        if (member != null) {
            entityManager.remove(member);
        }
    }
}
