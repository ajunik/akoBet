package com.akoBet.services;

import com.akoBet.entity.Bet;
import com.akoBet.entity.League;
import com.akoBet.entity.Match;
import com.akoBet.entity.Set;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Arek on 09.01.2017.
 */
public interface MatchService {

    public Match save(Match match);

    public List<Match> init(Set set) throws ParseException;

    List<Match> findByLeague(Long leagueId);

    public Integer getActualRound(League league);

    public Match findById(Long id);

    public List<Match> findAll();

    public void setResults(Bet bet);

    public List<Match> findByRound(Integer round);

}
