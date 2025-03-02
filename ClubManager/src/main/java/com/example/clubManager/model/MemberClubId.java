package com.example.clubManager.model;
import java.io.Serializable;

public class MemberClubId implements Serializable {

    private int idMembre;
    private int idClub;

    // Getters and Setters
    public int getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(int idMembre) {
        this.idMembre = idMembre;
    }

    public int getIdClub() {
        return idClub;
    }

    public void setIdClub(int idClub) {
        this.idClub = idClub;
    }

    // hashCode and equals methods
    @Override
    public int hashCode() {
        return idMembre + idClub;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MemberClubId that = (MemberClubId) obj;
        return idMembre == that.idMembre && idClub == that.idClub;
    }
}
