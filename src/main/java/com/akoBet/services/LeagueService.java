package com.akoBet.services;

import com.akoBet.entity.League;
import com.akoBet.entity.UserRest;

import java.util.List;

/**
 * Created by Arek on 06.01.2017.
 */
public interface LeagueService {

    public List<League> findAll();

    public League findById(Long id);

    public League findByName(String name);

    public List<UserRest> getPlayersApi(Long leagueId);

    public League save(League league);

    public void generateScheduler(Long leagueId);
}
