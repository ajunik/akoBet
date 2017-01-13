package com.akoBet.services;

import com.akoBet.entity.Duel;
import com.akoBet.entity.DuelRest;
import com.akoBet.entity.League;

import java.util.List;

/**
 * Created by Arek on 09.01.2017.
 */
public interface DuelService {

    public Duel save(Duel duel);

    public Duel findById(Long id);

    public List<Duel> findByRound(Integer round);

    public List<Duel> findByLeague(League league);

    public List<DuelRest> getDuelsApi(Long leagueId);
}
