package com.example.clubManager.model;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "membre_club")
public class MemberClub {

    @EmbeddedId
    private MemberClubId id;

    @ManyToOne
    @MapsId("idMembre")
    @JoinColumn(name = "ID_MEMBRE", referencedColumnName = "ID_MEMBRE", insertable = false, updatable = false)
    private Member member;

    @ManyToOne
    @MapsId("idClub")
    @JoinColumn(name = "ID_CLUB", referencedColumnName = "ID_CLUB", insertable = false, updatable = false)
    private Club club;

    @Column(name = "DATE_INSCRIPTION")
    private Date dateInscription;

    // Getters and Setters
    public MemberClubId getId() {
        return id;
    }

    public void setId(MemberClubId id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }
}
