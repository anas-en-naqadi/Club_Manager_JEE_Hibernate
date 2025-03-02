package com.example.clubManager.service;

import javax.persistence.EntityManager;

import com.example.clubManager.dao.MemberDao;
import com.example.clubManager.model.Member;

import java.util.List;

public class MemberService {

    private MemberDao memberDao;

    public MemberService(EntityManager entityManager) {
        this.memberDao = new MemberDao(entityManager);
    }

    // Ajouter un membre
    public void addMember(Member member) {
        if (member != null && member.getEmail() != null && !member.getEmail().isEmpty()) {
            memberDao.addMember(member);
        } else {
            throw new IllegalArgumentException("L'email du membre est requis.");
        }
    }

    // Récupérer un membre par son ID
    public Member getMemberById(int id) {
        Member member = memberDao.getMemberById(id);
        if (member == null) {
            throw new IllegalArgumentException("Aucun membre trouvé avec l'ID: " + id);
        }
        return member;
    }

    // Récupérer tous les membres
    public List<Member> getAllMembers() {
        return memberDao.getAllMembers();
    }

    // Mettre à jour un membre
    public void updateMember(Member member) {
        if (member != null && member.getIdMember() > 0) {
            memberDao.updateMember(member);
        } else {
            throw new IllegalArgumentException("Le membre doit avoir un ID valide.");
        }
    }

    // Supprimer un membre
    public void deleteMember(int id) {
        Member member = memberDao.getMemberById(id);
        if (member != null) {
            memberDao.deleteMember(id);
        } else {
            throw new IllegalArgumentException("Aucun membre trouvé avec l'ID: " + id);
        }
    }
}
