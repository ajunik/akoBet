package com.akoBet.services;

import com.akoBet.entity.League;
import com.akoBet.entity.Match;
import com.akoBet.entity.Set;
import com.akoBet.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arek on 09.01.2017.
 */
@Service
public class MatchServiceImpl implements MatchService {


    @Autowired
    MatchRepository matchRepository;

    @Autowired
    LeagueService leagueService;

    @Override
    public Match save(Match match) {
        return matchRepository.saveAndFlush(match);
    }

    public List<Match> init(Set set) throws ParseException {
        List<Match> matches = new ArrayList<>();
        matches.add(new Match(set.getM1t1(), set.getM1t2(), leagueService.findById(set.getLeagueId()), set.getRound(), set.getDate()));
        matches.add(new Match(set.getM2t1(), set.getM2t2(), leagueService.findById(set.getLeagueId()), set.getRound(), set.getDate()));
        matches.add(new Match(set.getM3t1(), set.getM3t2(), leagueService.findById(set.getLeagueId()), set.getRound(), set.getDate()));
        matches.add(new Match(set.getM4t1(), set.getM4t2(), leagueService.findById(set.getLeagueId()), set.getRound(), set.getDate()));
        matches.add(new Match(set.getM5t1(), set.getM5t2(), leagueService.findById(set.getLeagueId()), set.getRound(), set.getDate()));
        return matches;
    }

    @Override
    public List<Match> findByLeague(Long leagueId) {
        League league = leagueService.findById(leagueId);
        return matchRepository.findByLeague(league);
    }


    @Override
    public Integer getActualRound(League league) {

        Integer round = 0;
        List<Match> matches = matchRepository.findByLeague(league);


        for (Match match : matches) {
            if (match.getRound() > round) {
                round = match.getRound();
            }
        }

        return round;
    }

    @Override
    public Match findById(Long id) {
        return matchRepository.findOne(id);
    }
}
