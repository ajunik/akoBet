package com.akoBet.entity;

import java.util.Collection;

/**
 * Created by Arek on 06.01.2017.
 */
public class UserRest {

    private Long id;
    private String name;
    private String mail;
    private String createdDate;
    private Integer matches;
    private Integer points;
    private String league;
    Collection auth;

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection getAuth() {
        return auth;
    }

    public void setAuth(Collection auth) {
        this.auth = auth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public Integer getMatches() {
        return matches;
    }

    public void setMatches(Integer matches) {
        this.matches = matches;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
