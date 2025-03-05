package com.example.clubManager.dao;

import org.hibernate.Session;
import com.example.clubManager.models.Club;
import java.util.List;
public class ClubDao {

    public void saveClub(Session session, Club club) {
        session.persist(club);
    }

    public Club getClubById(Session session, int id) {
        return session.get(Club.class, id);
    }

    public List<Club> getAllClubs(Session session) {
        return session.createQuery("from Club", Club.class).list();
    }

    public void updateClub(Session session, Club club) {
        session.merge(club);
    }

    public void deleteClub(Session session, int id) {
        Club club = getClubById(session, id);
        if (club != null) {
            session.remove(club);
        }
    }

    /** Returns clubs where the specified student is the president. */
    public List<Club> getClubsByPresident(Session session, int presidentId) {
        return session.createQuery("from Club where president.idEtudiant = :presidentId", Club.class)
                      .setParameter("presidentId", presidentId)
                      .list();
    }

    /** Returns clubs administered by the specified user. */
    public List<Club> getClubsByAdmin(Session session, int adminId) {
        return session.createQuery("from Club where utilisateur.idUtilisateur = :adminId", Club.class)
                      .setParameter("adminId", adminId)
                      .list();
    }
}