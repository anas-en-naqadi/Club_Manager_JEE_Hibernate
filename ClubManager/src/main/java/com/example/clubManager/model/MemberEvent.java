package com.example.clubManager.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "membre_evenement")
public class MemberEvent {

    @EmbeddedId
    private MemberEventId id;

    @ManyToOne
    @MapsId("idMembre")
    @JoinColumn(name = "ID_MEMBRE", referencedColumnName = "ID_MEMBRE", insertable = false, updatable = false)
    private Member member;

    @ManyToOne
    @MapsId("idEvenement")
    @JoinColumn(name = "ID_EVENEMENT", referencedColumnName = "ID_EVENEMENT", insertable = false, updatable = false)
    private Event event;

    @Column(name = "DATE_INSCRIPTION")
    private Date dateInscription;

    // Getters and Setters
    public MemberEventId getId() {
        return id;
    }

    public void setId(MemberEventId id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }
}
