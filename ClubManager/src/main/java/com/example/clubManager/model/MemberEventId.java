package com.example.clubManager.model;

import java.io.Serializable;

public class MemberEventId implements Serializable {

    private int idMembre;
    private int idEvenement;

    // Getters and Setters
    public int getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(int idMembre) {
        this.idMembre = idMembre;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    // hashCode and equals methods
    @Override
    public int hashCode() {
        return idMembre + idEvenement;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MemberEventId that = (MemberEventId) obj;
        return idMembre == that.idMembre && idEvenement == that.idEvenement;
    }
}
