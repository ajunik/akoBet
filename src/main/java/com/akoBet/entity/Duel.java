package com.akoBet.entity;

import javax.persistence.*;

/**
 * Created by Arek on 09.01.2017.
 */
@Entity
@Table(name = "DUELS")
public class Duel {

    @Id
    @GeneratedValue(generator = "duel_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "duel_id", sequenceName = "duel_id_seq")
    private Long id;
    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "player1Id", nullable = true)
    private User player1;
    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "player2Id", nullable = true)
    private User player2;
    private Integer round;
    private Integer player1Score;
    private Integer player2Score;
    @OneToOne(targetEntity = League.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "leagueId", nullable = true)
    private League league;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public User getPlayer1() {
        return player1;
    }

    public void setPlayer1(User player1) {
        this.player1 = player1;
    }

    public User getPlayer2() {
        return player2;
    }

    public void setPlayer2(User player2) {
        this.player2 = player2;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public Integer getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(Integer player1Score) {
        this.player1Score = player1Score;
    }

    public Integer getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer2Score(Integer player2Score) {
        this.player2Score = player2Score;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }
}
