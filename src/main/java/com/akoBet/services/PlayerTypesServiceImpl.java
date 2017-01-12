package com.akoBet.services;

import com.akoBet.entity.Match;
import com.akoBet.entity.User;
import com.akoBet.repository.PlayerTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Arek on 09.01.2017.
 */
@Service
public class PlayerTypesServiceImpl implements PlayerTypesService {

    @Autowired
    PlayerTypesRepository playerTypesRepository;


    @Override
    public char findTypeByMatchAndUser(Match match, User user) {
        return playerTypesRepository.findTypeByMatchAndUser(match, user);
    }
}
