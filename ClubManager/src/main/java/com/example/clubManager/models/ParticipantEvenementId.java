package com.example.clubManager.models;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
public class ParticipantEvenementId implements Serializable {
    private int idParticipant;
    private int idEvenement;

    public ParticipantEvenementId() {}

    public ParticipantEvenementId(int idParticipant, int idEvenement) {
        this.idParticipant = idParticipant;
        this.idEvenement = idEvenement;
    }

    public int getIdParticipant() { return idParticipant; }
    public void setIdParticipant(int idParticipant) { this.idParticipant = idParticipant; }
    public int getIdEvenement() { return idEvenement; }
    public void setIdEvenement(int idEvenement) { this.idEvenement = idEvenement; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipantEvenementId that = (ParticipantEvenementId) o;
        return idParticipant == that.idParticipant && idEvenement == that.idEvenement;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idParticipant, idEvenement);
    }
}