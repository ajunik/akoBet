package com.akoBet.entity;

import javax.persistence.*;

/**
 * Created by Arek on 09.01.2017.
 */
@Entity
@Table(name = "PLAYER_TYPES")
public class PlayerTypes {

    public PlayerTypes() {
    }

    public PlayerTypes(char type, User user, Match match) {
        this.type = type;
        this.user = user;
        this.match = match;
    }


    @Id
    @GeneratedValue(generator = "playerTypes_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "playerTypes_id", sequenceName = "playerTypes_id_seq")
    private Long id;
    private char type;

    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "playerId", nullable = true)
    private User user;

    @OneToOne(targetEntity = Match.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "matchId", nullable = true)
    private Match match;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
