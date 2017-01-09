package com.akoBet.entity;

import javax.persistence.*;

/**
 * Created by Arek on 09.01.2017.
 */
@Entity
@Table(name = "MATCHES")
public class Match {

    @Id
    @GeneratedValue(generator = "match_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "match_id", sequenceName = "match_id_seq")
    private Long id;
    private String team1;
    private String team2;
    private char result;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
