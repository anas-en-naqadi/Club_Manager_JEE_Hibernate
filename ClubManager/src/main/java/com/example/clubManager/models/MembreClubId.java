package com.example.clubManager.models;

import java.io.Serializable;
import java.util.Objects;

public class MembreClubId implements Serializable {
    private int idMembre;
    private int idClub;

    public MembreClubId() {}

    public MembreClubId(int idMembre, int idClub) {
        this.idMembre = idMembre;
        this.idClub = idClub;
    }

    public int getIdMembre() { return idMembre; }
    public void setIdMembre(int idMembre) { this.idMembre = idMembre; }
    public int getIdClub() { return idClub; }
    public void setIdClub(int idClub) { this.idClub = idClub; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MembreClubId that = (MembreClubId) o;
        return idMembre == that.idMembre && idClub == that.idClub;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMembre, idClub);
    }
}