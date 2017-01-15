package com.akoBet.entity;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Arek on 09.01.2017.
 */
@Entity
@Table(name = "MATCHES")
public class Match {

    @Transient
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");


    public Match(){}

    public Match(String team1, String team2, League league, Integer round, String date) throws ParseException {
        this.team1 = team1;
        this.team2 = team2;
        this.league = league;
        this.round = round;
        this.date = df.parse(date);
        this.result = '-';
    }


    @Id
    @GeneratedValue(generator = "match_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "match_id", sequenceName = "match_id_seq")
    private Long id;
    private String team1;
    private String team2;
    private Integer round;
    private char result;
    private Date date;
    @OneToOne(targetEntity = League.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "leagueId", nullable = true)
    private League league;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public char getResult() {
        return result;
    }

    public void setResult(char result) {
        this.result = result;
    }
}
