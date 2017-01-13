package com.akoBet.services;

import com.akoBet.entity.Duel;
import com.akoBet.entity.DuelRest;
import com.akoBet.entity.League;
import com.akoBet.repository.DuelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arek on 09.01.2017.
 */
@Service
public class DuelServiceImpl implements DuelService {

    @Autowired
    DuelRepository duelRepository;

    @Autowired
    LeagueService leagueService;

    @Override
    public Duel save(Duel duel) {
        return duelRepository.saveAndFlush(duel);
    }

    @Override
    public Duel findById(Long id) {
        return duelRepository.findOne(id);
    }

    @Override
    public List<Duel> findByRound(Integer round) {
        return duelRepository.findByRound(round);
    }

    @Override
    public List<Duel> findByLeague(League league) {
        return duelRepository.findByLeague(league);
    }

    @Override
    public List<DuelRest> getDuelsApi(Long leagueId) {
        List<Duel> duels = findByLeague(leagueService.findById(leagueId));
        List<DuelRest> restDuels = new ArrayList<>();
        for (Duel duel : duels) {
            DuelRest duelRest = new DuelRest();
            duelRest.setId(duel.getId());
            duelRest.setPlayer1(duel.getPlayer1().getUsername());
            duelRest.setPlayer2(duel.getPlayer2().getUsername());
            duelRest.setRound(duel.getRound());
            if (duel.getPlayer1Score() != null && duel.getPlayer2Score() != null) {
                duelRest.setScore1(duel.getPlayer1Score());
                duelRest.setScore2(duel.getPlayer2Score());
            }
            restDuels.add(duelRest);
        }
        return restDuels;
    }


}
