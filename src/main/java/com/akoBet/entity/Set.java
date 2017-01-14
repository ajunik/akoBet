package com.akoBet.entity;

import com.akoBet.services.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Arek on 14.01.2017.
 */
public class Set {

    @Autowired
    LeagueService leagueService;


    @Size(min = 1)
    private String m1t1;
    @Size(min = 1)
    private String m1t2;
    @Size(min = 1)
    private String m2t1;
    @Size(min = 1)
    private String m2t2;
    @Size(min = 1)
    private String m3t1;
    @Size(min = 1)
    private String m3t2;
    @Size(min = 1)
    private String m4t1;
    @Size(min = 1)
    private String m4t2;
    @Size(min = 1)
    private String m5t1;
    @Size(min = 1)
    private String m5t2;
    private Integer round;
    private String date;
    @NotNull
    private Long leagueId;


    public String getM1t1() {
        return m1t1;
    }

    public void setM1t1(String m1t1) {
        this.m1t1 = m1t1;
    }

    public String getM1t2() {
        return m1t2;
    }

    public void setM1t2(String m1t2) {
        this.m1t2 = m1t2;
    }

    public String getM2t1() {
        return m2t1;
    }

    public void setM2t1(String m2t1) {
        this.m2t1 = m2t1;
    }

    public String getM2t2() {
        return m2t2;
    }

    public void setM2t2(String m2t2) {
        this.m2t2 = m2t2;
    }

    public String getM3t1() {
        return m3t1;
    }

    public void setM3t1(String m3t1) {
        this.m3t1 = m3t1;
    }

    public String getM3t2() {
        return m3t2;
    }

    public void setM3t2(String m3t2) {
        this.m3t2 = m3t2;
    }

    public String getM4t1() {
        return m4t1;
    }

    public void setM4t1(String m4t1) {
        this.m4t1 = m4t1;
    }

    public String getM4t2() {
        return m4t2;
    }

    public void setM4t2(String m4t2) {
        this.m4t2 = m4t2;
    }

    public String getM5t1() {
        return m5t1;
    }

    public void setM5t1(String m5t1) {
        this.m5t1 = m5t1;
    }

    public String getM5t2() {
        return m5t2;
    }

    public void setM5t2(String m5t2) {
        this.m5t2 = m5t2;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
    }


}
