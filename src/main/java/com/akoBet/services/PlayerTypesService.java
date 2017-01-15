package com.akoBet.services;

import com.akoBet.entity.*;

import java.util.List;

/**
 * Created by Arek on 09.01.2017.
 */
public interface PlayerTypesService {

    public PlayerTypes findByMatchAndUser(Match match, User user);

    public void saveBet(Bet bet, User user);

    public List<PlayerTypes> findByUserId(Long userId);

    public List<RestTypes> getApi(Long userId);

}
