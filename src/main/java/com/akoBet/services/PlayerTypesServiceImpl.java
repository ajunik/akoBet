package com.akoBet.services;

import com.akoBet.entity.*;
import com.akoBet.repository.PlayerTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arek on 09.01.2017.
 */
@Service
public class PlayerTypesServiceImpl implements PlayerTypesService {

    @Autowired
    PlayerTypesRepository playerTypesRepository;

    @Autowired
    MatchService matchService;

    @Autowired
    UserService userService;


    @Override
    public PlayerTypes findByMatchAndUser(Match match, User user) {
        return playerTypesRepository.findByMatchAndUser(match, user);
    }

    @Override
    public void saveBet(Bet bet, User user) {
        String[] singleTypes = bet.getTypes().split(",");
        for (String type : singleTypes) {
            String[] temp = type.split("-");
            Long matchId = Long.valueOf(temp[0]);
            char userType = temp[1].charAt(0);

            PlayerTypes playerTypes = new PlayerTypes(userType, user, matchService.findById(matchId));
            playerTypesRepository.saveAndFlush(playerTypes);
        }
    }

    @Override
    public List<PlayerTypes> findByUserId(Long userId) {
        User user = userService.findUserById(userId);
        return playerTypesRepository.findByUser(user);
    }

    @Override
    public List<RestTypes> getApi(Long userId) {
        List<PlayerTypes> playerTypes = findByUserId(userId);
        List<RestTypes> restTypes = new ArrayList<>();
        for (PlayerTypes type : playerTypes) {
            RestTypes restType = new RestTypes();
            restType.setUserId(userId);
            restType.setRound(type.getMatch().getRound());
            restType.setMatchId(type.getMatch().getId());
            restType.setType(type.getType());
            restTypes.add(restType);
        }
        return restTypes;
    }
}
