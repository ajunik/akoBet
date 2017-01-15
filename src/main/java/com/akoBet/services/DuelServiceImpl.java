package com.akoBet.services;

import com.akoBet.entity.*;
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

    @Autowired
    MatchService matchService;

    @Autowired
    PlayerTypesService playerTypesService;

    @Autowired
    UserService userService;

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
    public void saveResults(Integer round) {
        List<Duel> duels = findByRound(round);

        for (Duel duel : duels) {
            User user1 = duel.getPlayer1();
            User user2 = duel.getPlayer2();
            Double user1stats;
            Double user2stats;
            Integer user1Score = 0;
            Integer user1Points = user1.getPoints();
            Integer user1Matches = user1.getMatches();
            Integer user1typesCorrect = user1.getTypesCorrect();
            Integer user1typesFull = user1.getTypesFull();
            Integer user2Score = 0;
            Integer user2Points = user2.getPoints();
            Integer user2Matches = user2.getMatches();
            Integer user2typesCorrect = user2.getTypesCorrect();
            Integer user2typesFull = user2.getTypesFull();
            List<Match> matches = matchService.findByRound(round);

            for (Match match : matches) {
                PlayerTypes pt1 = playerTypesService.findByMatchAndUser(match, user1);
                PlayerTypes pt2 = playerTypesService.findByMatchAndUser(match, user2);
                char type1 = pt1.getType();
                char type2 = pt1.getType();

                if (type1 == match.getResult()) {
                    user1Score++;
                }
                if (type2 == match.getResult()) {
                    user2Score++;
                }
            }
            duel.setPlayer1Score(user1Score);
            duel.setPlayer2Score(user2Score);
            save(duel);

            user1typesFull = user1typesFull + 5;
            user2typesFull = user2typesFull + 5;
            user1typesCorrect = user1typesCorrect + user1Score;
            user2typesCorrect = user2typesCorrect + user2Score;
            user1Matches = user1Matches + 1;
            user2Matches = user2Matches + 1;
            user1stats = ((double) user1typesCorrect / (double) user1typesFull) * 100.00;
            user2stats = ((double) user2typesCorrect / (double) user2typesFull) * 100.00;

            if (user1Score > user2Score) {
                user1Points = user1Points + 3;
            } else if (user1Score < user2Score) {
                user2Points = user2Points + 3;
            } else {
                user1Points = user1Points + 1;
                user2Points = user2Points + 1;
            }

            user1.setTypesCorrect(user1typesCorrect);
            user1.setTypesFull(user1typesFull);
            user1.setMatches(user1Matches);
            user1.setPoints(user1Points);
            user1.setStats(user1stats);
            user2.setTypesCorrect(user2typesCorrect);
            user2.setTypesFull(user2typesFull);
            user2.setMatches(user2Matches);
            user2.setPoints(user2Points);
            user2.setStats(user2stats);
            userService.save(user1);
            userService.save(user2);
        }
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
